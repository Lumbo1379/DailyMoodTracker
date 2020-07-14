package com.example.dailymoodtracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.data.MoodHistory;

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

        position = MoodHistory.getCount() - 1 - position; // Bottom, up - newest, oldest

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_row_layout, null);

        int mood = MoodHistory.getMood(position);
        String comment = MoodHistory.getComment(position);

        ImageView imageViewRectangle = (ImageView) convertView.findViewById(R.id.list_row_layout_image_rectangle);
        imageViewRectangle.setBackgroundColor(mood);

        TextView textView = (TextView) convertView.findViewById(R.id.list_row_layout_text_time);

        switch (position)
        {
            case 0:
                textView.setText("One day ago");
                break;
            case 1:
                textView.setText("Two days ago");
                break;
            case 2:
                textView.setText("Three days ago");
                break;
            case 3:
                textView.setText("Four days ago");
                break;
            case 4:
                textView.setText("Five days ago");
                break;
            case 5:
                textView.setText("Six days ago");
                break;
            case 6:
                textView.setText("Seven days ago");
                break;
        }

        if (!comment.isEmpty()) {
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
                    mToast = Toast.makeText(v.getContext(), MoodHistory.getComment(moodPosition), Toast.LENGTH_SHORT);
                    mToast.show();
                }
            });
        }

        return convertView;
    }
}
