package com.example.dailymoodtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private MoodViewPagerAdapter mAdapter;
    private TextView mDebugText;
    private ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.activity_main_pager_image);
        mDebugText = findViewById(R.id.activity_main_text_debug);
        mConstraintLayout = (ConstraintLayout)findViewById(R.id.activity_main);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mDebugText.setText(String.valueOf(position + positionOffset));

                int nextPosition;

                if (position + positionOffset >= position && position != mAdapter.mColors.size() - 1)
                    nextPosition = position + 1;
                else
                    nextPosition = position - 1;

                int resultColor = ColorUtils.blendARGB(mAdapter.mColors.get(position), mAdapter.mColors.get(nextPosition), Math.abs(positionOffset));
                mConstraintLayout.setBackgroundColor(resultColor);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        List<Integer> images = Arrays.asList(R.drawable.smiley_super_happy,
                R.drawable.smiley_happy,
                R.drawable.smiley_normal,
                R.drawable.smiley_disappointed,
                R.drawable.smiley_sad);

        List<Integer> colors = Arrays.asList(Color.RED,
                Color.LTGRAY,
                Color.BLUE,
                Color.GREEN,
                Color.YELLOW);

        mAdapter = new MoodViewPagerAdapter(images, colors);
        mViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        mViewPager2.setAdapter(mAdapter);
    }
}