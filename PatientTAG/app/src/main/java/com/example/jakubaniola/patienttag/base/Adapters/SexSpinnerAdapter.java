package com.example.jakubaniola.patienttag.base.Adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jakubaniola.patienttag.base.TransportObjects.Sex;

public class SexSpinnerAdapter extends ArrayAdapter<Sex> {

    public SexSpinnerAdapter(Context context, int resource, Sex[] items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTextColor(getContext().getResources().getColor(android.R.color.background_dark));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTextColor(getContext().getResources().getColor(android.R.color.background_dark));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        return view;
    }
}