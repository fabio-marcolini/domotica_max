package com.max.domotica.domoticamax;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Server
{
    public interface CompletionListener
    {
        void onCompletion(boolean isFailure);
    }

    private static State lastState = new State();

    public static State getState()
    {
        return lastState;
    }

    private static Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e("SERVER", "Response: " + response);
        }
    };
    private static Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error)
        {

            Log.e("SERVER", "Error: " + error.getMessage());
        }
    };

    public static void updateState(final Context context, final CompletionListener listener)
    {
        doGet(context, "globale=stato", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                lastState = State.fromResponse(response);
                listener.onCompletion(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
                listener.onCompletion(true);
            }
        });
    }

    public static void doGet(final Context context, String request)
    {
        doGet(context, request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "response: " + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void doGet(final Context context, String request, final Response.Listener<String> responseListener, final Response.ErrorListener errorListener)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Settings.getPrefs(context).getString(Settings.SERVER, Settings.SERVER_DEFAULT);
        if(!url.endsWith("/"))
        {
            url += "/";
        }

        url += "?" + request;

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("SERVER", "REsponse: " + response);
                if(responseListener != null) {
                    responseListener.onResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SERVER", "Error: " + error.toString());
                if(errorListener != null) {
                    errorListener.onErrorResponse(error);
                }
            }
        })
        {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return super.getBody();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "*/*");
                return headers;
            }
        };


        req.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        queue.add(req);
        queue.start();
    }
}
