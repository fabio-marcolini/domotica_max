package com.max.domotica.domoticamax;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public abstract class SliderItem implements GenericItem
{
    private int title;
    public SliderItem(int title)
    {
        this.title = title;
    }

    @Override
    public View createConvertView(int position, LayoutInflater inflater, ViewGroup parent)
    {
        return inflater.inflate(R.layout.slider_item, parent, false);
    }

    @Override
    public void update(View view)
    {
        TextView title = view.findViewById(R.id.title);
        title.setText(this.title);
        SeekBar slider = view.findViewById(R.id.slider);
        slider.setOnSeekBarChangeListener(onChange);
    }

    private SeekBar.OnSeekBarChangeListener onChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            Log.d("OW", "progress: " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {
            onStopChange(seekBar.getProgress());
        }
    };

    abstract void onStopChange(int progress);
}
