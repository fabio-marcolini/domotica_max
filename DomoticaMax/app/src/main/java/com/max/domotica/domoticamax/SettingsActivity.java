package com.max.domotica.domoticamax;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

public class SettingsActivity extends BaseListActivity
{
    @Override
    void addItems(List<GenericItem> items)
    {
        final SharedPreferences preferences = Settings.getPrefs(this);
        items.add(new InlineTextItem(R.string.settings_server) {
            @Override
            void textChanged(String text)
            {
                preferences.edit().putString(Settings.SERVER, text).apply();
            }

            @Override
            String getText()
            {
                return preferences.getString(Settings.SERVER, Settings.SERVER_DEFAULT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }
}
