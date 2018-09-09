package com.max.domotica.domoticamax;

import android.content.Context;
import android.content.Intent;

public class Navigate
{
    public static void toSound(Context context)
    {
        Intent intent = new Intent(context, SoundActivity.class);
        context.startActivity(intent);
    }
}
