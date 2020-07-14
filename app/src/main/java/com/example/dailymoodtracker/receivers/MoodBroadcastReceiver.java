package com.example.dailymoodtracker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.example.dailymoodtracker.R;
import com.example.dailymoodtracker.controller.MainActivity;
import com.example.dailymoodtracker.data.MoodHistory;

public class MoodBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MoodHistory.updateMoodHistory();
        MoodHistory.getPreferences().edit().putInt(MoodHistory.PREF_KEY_CURRENT_MOOD_ITEM, 1).apply();
    }
}
