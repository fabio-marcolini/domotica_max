package com.max.domotica.domoticamax;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MaxListAdapter extends BaseAdapter
{

    private List<GenericItem> items;

    public MaxListAdapter(List<GenericItem> items)
    {
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        GenericItem item = items.get(position);
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = item.createConvertView(position, inflater, parent);
        }

        item.update(convertView);
        return convertView;
    }
}
