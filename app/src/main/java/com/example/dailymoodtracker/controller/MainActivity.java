package com.example.dailymoodtracker.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.adapters.MoodViewPagerAdapter;
import com.example.dailymoodtracker.model.Mood;
import com.example.dailymoodtracker.data.MoodHistory;
import com.example.dailymoodtracker.receivers.MoodBroadcastReceiver;

import java.util.Arrays;
import java.util.Calendar;
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
    private MoodHistory mMoodHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewComponents(); // Set fields with components from main layout
        setAlarm(); // Used for updating mood history
        initializeViewPager();
        initializeViewPagerComponents(); // listeners for history and comment button
    }

    @Override
    public void onResume() {
        super.onResume();

        int prefItem = MoodHistory.getPreferences().getInt(MoodHistory.PREF_KEY_CURRENT_MOOD_ITEM, 1);

        if (mViewPager2.getCurrentItem() != prefItem) { // Passes when mood has been recent to default since last app activity, e.g. in mood history page 11:58pm to 01:02am
            mViewPager2.setCurrentItem(prefItem, false);
        }
    }

    private List<Mood> generateMoods() { // Moods displayed by view pager

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

    private void setAlarm() { // Alarm triggered once per day at 24:00 hrs
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MoodBroadcastReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 24); // Set time to trigger

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, alarmIntent); // Set repeat frequency (every 24 hrs)
    }

    private void initializeViewPager() {
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { // Blend colours from one mood to the other
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mMoodHistory.setCurrentMood(mMoodAdapter.getMoods().get(position).getColor());

                mDebugText.setText(String.valueOf(position + positionOffset));

                int nextPosition;

                if (position + positionOffset >= position && position != mMoodAdapter.getItemCount() - 1) // If not on most down mood
                    nextPosition = position + 1;
                else
                    nextPosition = position - 1; // If on last mood, cannot swipe up anymore

                int resultColor = ColorUtils.blendARGB(mMoodAdapter.getMoods().get(position).getColor(), mMoodAdapter.getMoods().get(nextPosition).getColor(), Math.abs(positionOffset));
                mConstraintLayout.setBackgroundColor(resultColor);
            }

            @Override
            public void onPageSelected(int position) { // Update preferences with current mood
                super.onPageSelected(position);
                MoodHistory.getPreferences().edit().putInt(MoodHistory.PREF_KEY_CURRENT_MOOD_ITEM, position).apply();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        mViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL); // Swipe up on down instead of left and right
        mViewPager2.setAdapter(mMoodAdapter);
        mViewPager2.setCurrentItem(1, false); // Set default image, with no smooth scroll
    }

    private void initializeViewPagerComponents() {
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Move from main layout to history layout
                Intent moodHistoryIntent = new Intent(MainActivity.this, MoodHistoryActivity.class);
                startActivityForResult(moodHistoryIntent, MOOD_HISTORY_ACTIVITY_REQUEST_CODE);
            }
        });

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Comment on mood
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Comment");

                View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogue_comment, null);
                final EditText input = (EditText)viewInflated.findViewById(R.id.dialogue_comment_input);
                builder.setView(viewInflated);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommentText = input.getText().toString();
                        mMoodHistory.setCurrentComment(mCommentText);
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
    }

    private void getViewComponents() {
        mMoodHistory = new MoodHistory(getPreferences(MODE_PRIVATE), Color.GREEN);
        mViewPager2 = findViewById(R.id.activity_main_pager_image);
        mDebugText = findViewById(R.id.activity_main_text_debug);
        mConstraintLayout = (ConstraintLayout)findViewById(R.id.activity_main);
        mHistoryButton = findViewById(R.id.activity_main_image_history);
        mCommentButton = findViewById(R.id.activity_main_image_comment);
        mMoodAdapter = new MoodViewPagerAdapter(generateMoods());
    }
}