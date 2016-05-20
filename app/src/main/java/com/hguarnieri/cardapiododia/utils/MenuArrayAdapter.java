package com.hguarnieri.cardapiododia.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hguarnieri.cardapiododia.R;

/**
 * Created by henrique on 15/01/14.
 */
public class MenuArrayAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private final String[] values;
    private final int[] icons;


    public MenuArrayAdapter(Context context, String[] values, int[] icons)
    {
        mInflater = LayoutInflater.from(context);
        this.values = values;
        this.icons = icons;
    }

    public int getCount()
    {
        return values.length;
    }

    public String getItem(int position)
    {
        return values[position];
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        view = mInflater.inflate(R.layout.menu_row_layout, null);

        ((TextView) view.findViewById(R.id.menu_row_layout_text)).setText(values[position]);
        ((ImageView) view.findViewById(R.id.menu_row_layout_icon)).setImageResource(icons[position]);

        return view;
    }
}