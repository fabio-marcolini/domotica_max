package com.max.domotica.domoticamax;

import android.widget.Toast;

import java.util.List;

public class LivingRoomActivity extends BaseListActivity
{
    @Override
    void addItems(List<GenericItem> items)
    {
        setTitle(R.string.living_room);

        SwitchItem livingRoomOnOff = new SwitchItem(R.string.music){

            @Override
            void onSwitch(boolean on)
            {
                String status = "0";
                if(on)
                {
                    status = "1";
                }

                Server.doGet(LivingRoomActivity.this, "status=" + status);
            }
        };

        SliderItem livingRoomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                Toast.makeText(LivingRoomActivity.this, "Volume Soggiorno: " + progress, Toast.LENGTH_SHORT).show();
            }
        };

        items.add(livingRoomOnOff);
        items.add(livingRoomVolume);
    }
}
