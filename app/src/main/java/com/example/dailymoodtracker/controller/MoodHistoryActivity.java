package com.example.dailymoodtracker.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.adapters.MoodHistoryAdapter;

public class MoodHistoryActivity extends AppCompatActivity {

    private ListView mMoodList;
    private MoodHistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        mMoodList = findViewById(R.id.activity_mood_history_list_view_moods);
        mHistoryAdapter = new MoodHistoryAdapter(this);

        mMoodList.setAdapter(mHistoryAdapter);
    }
}