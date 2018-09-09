package com.max.domotica.domoticamax;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface GenericItem
{
    View createConvertView(int position, LayoutInflater inflater, ViewGroup parent);

    void update(View view);
}
