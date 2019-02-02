package com.max.domotica.domoticamax;

import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class BathroomActivity extends BaseListActivity
{
    @Override
    void addItems(List<GenericItem> items)
    {
        setTitle(R.string.bathroom);

        SwitchItem amplificatoreOnOff = new SwitchItem(R.string.amplificatore){

            @Override
            void onSwitch(boolean on)
            {
                String status = "0";
                if(on)
                {
                    status = "1";
                }

                Server.doGet(BathroomActivity.this, "status=" + status);
                //Toast.makeText(BathroomActivity.this, "Ampli on? " + on, Toast.LENGTH_SHORT).show();
            }

            @Override
            void setCurrentState(View view, Switch switchButton)
            {
                switchButton.setChecked(Server.getState().amplificatorOn);
            }

        };

        SwitchItem bathroomOnOff = new SwitchItem(R.string.music){

            @Override
            void onSwitch(boolean on)
            {
                String status = "0";
                if(on)
                {
                    status = "1";
                }

                Server.doGet(BathroomActivity.this, "stanza0Ampli=" + status);
                //Toast.makeText(BathroomActivity.this, "Bagno on? " + on, Toast.LENGTH_SHORT).show();
            }

            @Override
            void setCurrentState(View view, Switch switchButton)
            {
                switchButton.setChecked(Server.getState().ampliStanza0On);
            }
        };

        SliderItem bathroomVolume = new SliderItem(R.string.volume){
            @Override
            void onStopChange(int progress)
            {
                String volume = String.format("%03d", progress);
                Server.doGet(BathroomActivity.this, "stanza0Vol=" + volume);
            }

            @Override
            protected void setCurrentStatus(View view, SeekBar slider)
            {
                slider.setProgress(Server.getState().stanza0Vol);
            }
        };

        items.add(amplificatoreOnOff);
        items.add(bathroomOnOff);
        items.add(bathroomVolume);
    }
}