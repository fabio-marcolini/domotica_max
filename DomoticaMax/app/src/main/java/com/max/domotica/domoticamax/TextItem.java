package com.max.domotica.domoticamax;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class TextItem implements GenericItem
{
    private int title;
    public TextItem(int title)
    {
        this.title = title;
    }

    @Override
    public View createConvertView(final int position, LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.text_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextItem.this.onClick(v);
            }
        });
        return view;
    }

    @Override
    public void update(View view)
    {
        TextView title = view.findViewById(R.id.title);
        title.setText(this.title);
    }

    abstract void onClick(View v);

}
