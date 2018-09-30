package com.max.domotica.domoticamax;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class SoundActivity  extends BaseListActivity
{
    @Override
    void addItems(List<GenericItem> items)
    {
        setTitle(R.string.sounds_title);

        TextItem onkyo = new TextItem(R.string.onkyo)
        {
            @Override
            void onClick(View v)
            {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.onkyo.jp.onkyocontroller");

                if (launchIntent != null) {
                    startActivity(launchIntent);
                }else{
                    Toast.makeText(SoundActivity.this, "Applicazione Onkyo non trovata", Toast.LENGTH_SHORT).show();
                }
            }
        };

        SwitchItem livingRoomOnOff = new SwitchItem(R.string.living_room){

            @Override
            void onSwitch(boolean on)
            {
                Toast.makeText(SoundActivity.this, "Soggiorno on? " + on, Toast.LENGTH_SHORT).show();
            }
        };

        SwitchItem bathroomOnOff = new SwitchItem(R.string.bathroom){

            @Override
            void onSwitch(boolean on)
            {
                Toast.makeText(SoundActivity.this, "Bagno on? " + on, Toast.LENGTH_SHORT).show();
            }
        };

        SliderItem livingRoomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                Toast.makeText(SoundActivity.this, "Volume Soggiorno: " + progress, Toast.LENGTH_SHORT).show();
            }
        };
        SliderItem bathroomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                Toast.makeText(SoundActivity.this, "Volume Bagno: " + progress, Toast.LENGTH_SHORT).show();
            }
        };

        items.add(onkyo);
        items.add(livingRoomOnOff);
        items.add(livingRoomVolume);
        items.add(bathroomOnOff);
        items.add(bathroomVolume);
    }




}
