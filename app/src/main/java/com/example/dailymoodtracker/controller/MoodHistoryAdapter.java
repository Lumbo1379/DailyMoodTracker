package com.example.dailymoodtracker.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.model.Mood;

import java.util.ArrayList;
import java.util.List;

public class MoodHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<Mood> mMoods;

    public MoodHistoryAdapter(Context context) {
        mContext = context;
        mMoods = new ArrayList<Mood>();

        Mood test = new Mood(R.drawable.smiley_super_happy, Color.RED);
        mMoods.add(test);
    }

    @Override
    public int getCount() {
        return mMoods.size();
    }

    @Override
    public Object getItem(int position) {
        return mMoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mood mood = mMoods.get(position);

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_row_layout, null);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.list_row_layout_image_rectangle);
        imageView.setBackgroundColor(mood.getImage());

        return convertView;
    }

    public void addMood(Mood mood) {
        mMoods.add(mood);
    }
}
