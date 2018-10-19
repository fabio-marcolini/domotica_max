package com.max.domotica.domoticamax;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class State
{
    public int volume = 0;

    public boolean amplificatorOn = false;

    public static State fromResponse(String response)
    {
        HashMap<String, String> stateMap = new HashMap<>();
        String[] lines = response.split("[\\r\\n]+");
        for(String line : lines)
        {

            String[] keyValue = line.split(":");
            if(keyValue.length < 2)
            {
                Log.e("STATE", "Cannot read state line: " + line);
                continue;
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            stateMap.put(key, value);
        }

        State state = new State();
        state.volume = readInt(stateMap, "volume", 0);
        int ampli = readInt(stateMap, "ampli", 0);
        state.amplificatorOn = ampli == 1 ? true : false;
        return state;
    }

    private static int readInt(Map<String, String> state, String key, int defaultValue)
    {
        String value = state.get(key);
        if(value == null)
        {
            Log.e("STATE", "Key: " + key + " not found in state map");
            return defaultValue;
        }

        try
        {
            return Integer.parseInt(value);
        }
        catch(Exception e)
        {
            Log.e("STATE", "Failed to parse int value: " + value + " for key: " + key, e);
            return defaultValue;
        }
    }
}
