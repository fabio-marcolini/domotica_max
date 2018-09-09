package com.max.domotica.domoticamax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseListActivity {

    private List<GenericItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void addItems(List<GenericItem> items)
    {
        TextItem sounds = new TextItem(R.string.sounds_title){
            @Override
            public void onClick(View view)
            {
                Navigate.toSound(view.getContext());
            }
        };
        items.add(sounds);
    }


}
