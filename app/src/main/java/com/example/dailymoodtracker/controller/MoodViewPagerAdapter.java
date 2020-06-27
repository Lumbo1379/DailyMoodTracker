package com.example.dailymoodtracker.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.model.Mood;

import java.util.List;

public class MoodViewPagerAdapter extends RecyclerView.Adapter<MoodViewPagerAdapter.MoodViewHolder> {

    public List<Mood> getMoods() {
        return moods;
    }

    public void setMoods(List<Mood> moods) {
        this.moods = moods;
    }

    private List<Mood> moods;
    private TextView mDebugText;
    private ViewPager2 mViewPager2;

    public MoodViewPagerAdapter(List<Mood> moods) {
        setMoods(moods);
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item_view_pager, parent, false);
        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        int currentImage = moods.get(position).getImage();
        holder.mImageView.setImageResource(currentImage);
    }

    @Override
    public int getItemCount() {
        return moods.size();
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.mood_item_view_pager_image_view);
        }
    }
}
