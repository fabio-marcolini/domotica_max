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

    public static void toBathroom(Context context)
    {
        Intent intent = new Intent(context, BathroomActivity.class);
        context.startActivity(intent);
    }

    public static void toLivingRoom(Context context)
    {
        Intent intent = new Intent(context, LivingRoomActivity.class);
        context.startActivity(intent);
    }

    public static void toSettings(Context context)
    {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}
