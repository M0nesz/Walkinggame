package com.example.walkinggame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mData;

    public MyGridAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        }

        convertView.setBackgroundColor(Color.rgb(63, 78, 79));
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(mData.get(position));

        return convertView;
    }
}
