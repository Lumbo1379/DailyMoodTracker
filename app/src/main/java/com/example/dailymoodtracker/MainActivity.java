package com.example.dailymoodtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private MoodViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.activity_main_image_pager);

        List<Integer> images = Arrays.asList(R.drawable.smiley_super_happy,
                R.drawable.smiley_happy,
                R.drawable.smiley_normal,
                R.drawable.smiley_disappointed,
                R.drawable.smiley_sad);

        mAdapter = new MoodViewPagerAdapter(images);
        mViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        mViewPager2.setAdapter(mAdapter);
    }
}