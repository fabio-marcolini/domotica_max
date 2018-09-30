package com.max.domotica.domoticamax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseListActivity {

    private List<GenericItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void addItems(List<GenericItem> items)
    {
        TextItem livingRoom = new TextItem(R.string.living_room)
        {
            @Override
            public void onClick(View view)
            {
                Navigate.toLivingRoom(view.getContext());
            }
        };
        items.add(livingRoom);

        TextItem bathroom = new TextItem(R.string.bathroom)
        {
            @Override
            public void onClick(View view)
            {
                Navigate.toBathroom(view.getContext());
            }
        };
        items.add(bathroom);

        TextItem onkyo = new TextItem(R.string.onkyo)
        {
            @Override
            void onClick(View v)
            {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.onkyo.jp.onkyocontroller");

                if (launchIntent != null) {
                    startActivity(launchIntent);
                }else{
                    Toast.makeText(MainActivity.this, "Applicazione Onkyo non trovata", Toast.LENGTH_SHORT).show();
                }
            }
        };

        items.add(onkyo);
    }




}
