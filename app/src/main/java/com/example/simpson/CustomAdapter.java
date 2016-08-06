package com.example.simpson;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import java.util.List;

/**
 * Created by admin on 8/1/2016.
 */
public class CustomAdapter extends ArrayAdapter<SimCharacter> {

    public CustomAdapter(Context context, List<SimCharacter> list) {
        super(context, R.layout.list_item, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        SimCharacter aCharcter= getItem(position);

        TextView aLine = (TextView) convertView.findViewById(R.id.l_item_txt);
        aLine.setText( aCharcter.name);

        return convertView;
    }
}