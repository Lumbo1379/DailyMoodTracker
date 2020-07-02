package com.example.dailymoodtracker.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.model.Mood;
import com.example.dailymoodtracker.model.MoodHistory;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MOOD_HISTORY_ACTIVITY_REQUEST_CODE = 42;

    private ViewPager2 mViewPager2;
    private MoodViewPagerAdapter mMoodAdapter;
    private TextView mDebugText;
    private ConstraintLayout mConstraintLayout;
    private ImageView mHistoryButton;
    private ImageView mCommentButton;
    private String mCommentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.activity_main_pager_image);
        mDebugText = findViewById(R.id.activity_main_text_debug);
        mConstraintLayout = (ConstraintLayout)findViewById(R.id.activity_main);
        mHistoryButton = findViewById(R.id.activity_main_image_history);
        mCommentButton = findViewById(R.id.activity_main_image_comment);
        mMoodAdapter = new MoodViewPagerAdapter(generateMoods());

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mDebugText.setText(String.valueOf(position + positionOffset));

                int nextPosition;

                if (position + positionOffset >= position && position != mMoodAdapter.getItemCount() - 1)
                    nextPosition = position + 1;
                else
                    nextPosition = position - 1;

                int resultColor = ColorUtils.blendARGB(mMoodAdapter.getMoods().get(position).getColor(), mMoodAdapter.getMoods().get(nextPosition).getColor(), Math.abs(positionOffset));
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

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moodHistoryIntent = new Intent(MainActivity.this, MoodHistoryActivity.class);
                startActivityForResult(moodHistoryIntent, MOOD_HISTORY_ACTIVITY_REQUEST_CODE);
            }
        });

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Comment");

                View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogue_comment, null);
                final EditText input = (EditText)viewInflated.findViewById(R.id.dialogue_comment_input);
                builder.setView(viewInflated);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommentText = input.getText().toString();

                        Mood mood = new Mood(R.drawable.smiley_happy, Color.GREEN);
                        MoodHistory.addMood(mood);
                        MoodHistory.getMood(0).setComment(mCommentText);
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        mViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        mViewPager2.setAdapter(mMoodAdapter);
        mViewPager2.setCurrentItem(1, false); // Set default image, with no smooth scroll
    }

    private List<Mood> generateMoods() {

        Mood superHappy = new Mood(R.drawable.smiley_super_happy, Color.YELLOW);
        Mood happy = new Mood(R.drawable.smiley_happy, Color.GREEN);
        Mood normal = new Mood(R.drawable.smiley_normal, Color.BLUE);
        Mood disappointed = new Mood(R.drawable.smiley_disappointed, Color.LTGRAY);
        Mood sad = new Mood(R.drawable.smiley_sad, Color.RED);

        return Arrays.asList(superHappy,
                happy,
                normal,
                disappointed,
                sad);
    }
}