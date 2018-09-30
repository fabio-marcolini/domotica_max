package com.max.domotica.domoticamax;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public abstract class InlineTextItem implements GenericItem
{
    private int title;

    public InlineTextItem(int title)
    {
        this.title = title;
    }

    @Override
    public View createConvertView(int position, LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.inline_text_item, parent, false);
        EditText text = view.findViewById(R.id.text);
        text.addTextChangedListener(watcher);
        return view;
    }

    @Override
    public void update(View view)
    {
        TextView title = view.findViewById(R.id.title);
        title.setText(this.title);
        EditText text = view.findViewById(R.id.text);
        if(text.getText().toString().equals(getText()))
        {
            return;
        }

        text.setText(getText());
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if(getText().equals(s.toString()))
            {
                return;
            }

            textChanged(s.toString());
        }
    };

    abstract void textChanged(String text);

    abstract String getText();
}
