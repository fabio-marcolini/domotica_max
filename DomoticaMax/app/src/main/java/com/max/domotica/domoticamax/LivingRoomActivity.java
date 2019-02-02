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

        SwitchItem amplificatoreOnOff = new SwitchItem(R.string.amplificatore){

            @Override
            void onSwitch(boolean on)
            {
                String status = "0";
                if(on)
                {
                    status = "1";
                }

                Server.doGet(LivingRoomActivity.this, "status=" + status);
                //Toast.makeText(LivingRoomActivity.this, "Ampli on? " + on, Toast.LENGTH_SHORT).show();
            }

            @Override
            void setCurrentState(View view, Switch switchButton)
            {
                switchButton.setChecked(Server.getState().amplificatorOn);
            }
        };

        SwitchItem livingRoomOnOff = new SwitchItem(R.string.music){

            @Override
            void onSwitch(boolean on)
            {
                String status = "0";
                if(on)
                {
                    status = "1";
                }

                Server.doGet(LivingRoomActivity.this, "stanza2Ampli=" + status);
            }

            @Override
            void setCurrentState(View view, Switch switchButton)
            {
                switchButton.setChecked(Server.getState().ampliStanza2On);
            }
        };

        SliderItem livingRoomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                String volume = String.format("%03d", progress);
                Server.doGet(LivingRoomActivity.this, "stanza2Vol=" + volume);
            }

            @Override
            protected void setCurrentStatus(View view, SeekBar slider)
            {
                slider.setProgress(Server.getState().stanza2Vol);
            }
        };

        items.add(amplificatoreOnOff);
        items.add(livingRoomOnOff);
        items.add(livingRoomVolume);
    }
}
