package com.example.dailymoodtracker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class MoodViewPagerAdapter extends RecyclerView.Adapter<MoodViewPagerAdapter.MoodViewHolder> {

    public List<Integer> mImages;
    public List<Integer> mColors;

    private TextView mDebugText;
    private ViewPager2 mViewPager2;

    public MoodViewPagerAdapter(List<Integer> images, List<Integer> colors) {
        mImages = images;
        mColors = colors;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item_view_pager, parent, false);

        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        int currentImage = mImages.get(position);
        holder.mImageView.setImageResource(currentImage);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.mood_item_view_pager_image_view);
        }
    }
}
