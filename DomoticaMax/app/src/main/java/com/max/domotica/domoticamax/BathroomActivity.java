package com.max.domotica.domoticamax;

import android.widget.Toast;

import java.util.List;

public class BathroomActivity extends BaseListActivity
{
    @Override
    void addItems(List<GenericItem> items)
    {
        setTitle(R.string.living_room);

        SwitchItem livingRoomOnOff = new SwitchItem(R.string.music){

            @Override
            void onSwitch(boolean on)
            {
                Toast.makeText(BathroomActivity.this, "Bagno on? " + on, Toast.LENGTH_SHORT).show();
            }
        };

        SliderItem livingRoomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                Toast.makeText(BathroomActivity.this, "Volume Bagno: " + progress, Toast.LENGTH_SHORT).show();
            }
        };

        items.add(livingRoomOnOff);
        items.add(livingRoomVolume);
    }
}