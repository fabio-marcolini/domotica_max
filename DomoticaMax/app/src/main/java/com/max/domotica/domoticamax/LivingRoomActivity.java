package com.max.domotica.domoticamax;

import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
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

            @Override
            void setCurrentState(View view, Switch switchButton)
            {
                switchButton.setChecked(Server.getState().amplificatorOn);
            }
        };

        SliderItem livingRoomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                String volume = String.format("%03d", progress);
                Server.doGet(LivingRoomActivity.this, "volume=" + volume);
            }

            @Override
            protected void setCurrentStatus(View view, SeekBar slider)
            {
                slider.setProgress(Server.getState().volume);
            }
        };

        items.add(livingRoomOnOff);
        items.add(livingRoomVolume);
    }
}
