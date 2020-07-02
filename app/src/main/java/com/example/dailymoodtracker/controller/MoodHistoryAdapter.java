package com.example.dailymoodtracker.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.model.Mood;
import com.example.dailymoodtracker.model.MoodHistory;

import java.util.ArrayList;
import java.util.List;

public class MoodHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private Toast mToast;

    public MoodHistoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return MoodHistory.getCount();
    }

    @Override
    public Object getItem(int position) {
        return MoodHistory.getMood(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_row_layout, null);

        Mood mood = MoodHistory.getMood(position);

        ImageView imageViewRectangle = (ImageView) convertView.findViewById(R.id.list_row_layout_image_rectangle);
        imageViewRectangle.setBackgroundColor(mood.getColor());

        if (!mood.getComment().isEmpty()) {
            ImageView imageViewComment = (ImageView) convertView.findViewById(R.id.list_row_layout_image_comment);
            imageViewComment.setTag(position);
            imageViewComment.setVisibility(View.VISIBLE);

            imageViewComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mToast != null) {
                        mToast.cancel();
                    }

                    int moodPosition = (int)v.getTag();
                    mToast = Toast.makeText(v.getContext(), MoodHistory.getMood(moodPosition).getComment(), Toast.LENGTH_SHORT);
                    mToast.show();
                }
            });
        }

        return convertView;
    }
}
