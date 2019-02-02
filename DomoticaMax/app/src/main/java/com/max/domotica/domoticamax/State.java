package com.max.domotica.domoticamax;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class State
{
    public int stanza0Vol = 0;
    public int stanza2Vol = 0;

    public boolean amplificatorOn = false;
    public boolean ampliStanza0On = false;
    public boolean ampliStanza2On = false;

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
        state.stanza0Vol = readInt(stateMap, "stanza0Vol", 0);
        state.stanza2Vol = readInt(stateMap, "stanza2Vol", 0);
        int ampli = readInt(stateMap, "status", 0);
        int stanza0Ampli = readInt(stateMap, "stanza0Ampli", 0);
        int stanza2Ampli = readInt(stateMap, "stanza2Ampli", 0);
        state.amplificatorOn = ampli == 1 ? true : false;
        state.ampliStanza0On = stanza0Ampli == 1 ? true : false;
        state.ampliStanza2On = stanza2Ampli == 1 ? true : false;
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
