package com.example.android.waterborne.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.waterborne.R;

import java.util.List;

public class TestListViewAdapter extends ArrayAdapter<Object> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public TestListViewAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            switch (getItemViewType(position)) {
                case TYPE_HEADER: {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.tools_list_item_card_big, parent, false);
                }
                break;
                case TYPE_CELL: {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.tools_list_item_card_small, parent, false);
                }
                break;
            }
        }
        return convertView;
    }
}