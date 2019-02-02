package com.max.domotica.domoticamax;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings
{
    public static final String SERVER = "request_server";

    public static final String SERVER_DEFAULT = "http://192.168.1.100";

    public static SharedPreferences getPrefs(Context context)
    {
        return context.getSharedPreferences("global_settings", Context.MODE_PRIVATE);
    }
}
