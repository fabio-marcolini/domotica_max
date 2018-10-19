package com.max.domotica.domoticamax;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public abstract class SwitchItem  implements GenericItem
{
    private int text;
    public SwitchItem(int text)
    {
            this.text = text;
    }

    @Override
    public View createConvertView(int position, LayoutInflater inflater, ViewGroup parent)
    {
        return inflater.inflate(R.layout.switch_item, parent,false);
    }

    @Override
    public void update(View view)
    {
        TextView text = view.findViewById(R.id.title);
        text.setText(this.text);
        Switch switchButton = view.findViewById(R.id.switch_button);
        setCurrentState(view, switchButton);
        switchButton.setOnCheckedChangeListener(onCheck);
    }

    void setCurrentState(View view, Switch switchButton)
    {

    }

    abstract void onSwitch(boolean on);

    private CompoundButton.OnCheckedChangeListener onCheck = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            onSwitch(isChecked);
        }
    };
}
