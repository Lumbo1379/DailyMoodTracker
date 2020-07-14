package com.example.dailymoodtracker.data;

import android.content.SharedPreferences;
import android.graphics.Color;

import com.example.dailymoodtracker.R;

import java.util.ArrayList;
import java.util.List;

public class MoodHistory {

    public static final String PREF_KEY_CURRENT_MOOD_ITEM = "PREF_KEY_CURRENT_MOOD_ITEM";

    public static final String PREF_KEY_DAYS_PAST = "PREF_KEY_DAYS_PAST";

    public static final String PREF_KEY_COLOR_CURRENT = "PREF_KEY_COLOR_CURRENT";
    public static final String PREF_KEY_COLOR_DAY_1 = "PREF_KEY_COLOR_DAY_1";
    public static final String PREF_KEY_COLOR_DAY_2 = "PREF_KEY_COLOR_DAY_2";
    public static final String PREF_KEY_COLOR_DAY_3 = "PREF_KEY_COLOR_DAY_3";
    public static final String PREF_KEY_COLOR_DAY_4 = "PREF_KEY_COLOR_DAY_4";
    public static final String PREF_KEY_COLOR_DAY_5 = "PREF_KEY_COLOR_DAY_5";
    public static final String PREF_KEY_COLOR_DAY_6 = "PREF_KEY_COLOR_DAY_6";
    public static final String PREF_KEY_COLOR_DAY_7 = "PREF_KEY_COLOR_DAY_7";

    public static final String PREF_KEY_COMMENT_CURRENT = "PREF_KEY_COMMENT_CURRENT";
    public static final String PREF_KEY_COMMENT_DAY_1 = "PREF_KEY_COMMENT_DAY_1";
    public static final String PREF_KEY_COMMENT_DAY_2 = "PREF_KEY_COMMENT_DAY_2";
    public static final String PREF_KEY_COMMENT_DAY_3 = "PREF_KEY_COMMENT_DAY_3";
    public static final String PREF_KEY_COMMENT_DAY_4 = "PREF_KEY_COMMENT_DAY_4";
    public static final String PREF_KEY_COMMENT_DAY_5 = "PREF_KEY_COMMENT_DAY_5";
    public static final String PREF_KEY_COMMENT_DAY_6 = "PREF_KEY_COMMENT_DAY_6";
    public static final String PREF_KEY_COMMENT_DAY_7 = "PREF_KEY_COMMENT_DAY_7";

    private static int DEFAULT_MOOD;

    public static SharedPreferences getPreferences() {
        return mPreferences;
    }

    private static SharedPreferences mPreferences;

    public MoodHistory(SharedPreferences preferences, int defaultMood) {
        mPreferences = preferences;

        DEFAULT_MOOD = defaultMood;
        mPreferences.edit().putInt(PREF_KEY_COLOR_CURRENT, defaultMood).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_CURRENT, "").apply();
    }

    public void setCurrentMood(int color) {
        mPreferences.edit().putInt(PREF_KEY_COLOR_CURRENT, color).apply();
    }

    public void setCurrentComment(String comment) {
        mPreferences.edit().putString(PREF_KEY_COMMENT_CURRENT, comment).apply();
    }

    public static void updateMoodHistory() {
        mPreferences.edit().putInt(PREF_KEY_DAYS_PAST, mPreferences.getInt(PREF_KEY_DAYS_PAST, 0) + 1).apply();

        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_7, mPreferences.getInt(PREF_KEY_COLOR_DAY_6, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_6, mPreferences.getInt(PREF_KEY_COLOR_DAY_5, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_5, mPreferences.getInt(PREF_KEY_COLOR_DAY_4, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_4, mPreferences.getInt(PREF_KEY_COLOR_DAY_3, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_3, mPreferences.getInt(PREF_KEY_COLOR_DAY_2, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_2, mPreferences.getInt(PREF_KEY_COLOR_DAY_1, DEFAULT_MOOD)).apply();
        mPreferences.edit().putInt(PREF_KEY_COLOR_DAY_1, mPreferences.getInt(PREF_KEY_COLOR_CURRENT, DEFAULT_MOOD)).apply();

        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_7, mPreferences.getString(PREF_KEY_COMMENT_DAY_6, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_6, mPreferences.getString(PREF_KEY_COMMENT_DAY_5, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_5, mPreferences.getString(PREF_KEY_COMMENT_DAY_4, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_4, mPreferences.getString(PREF_KEY_COMMENT_DAY_3, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_3, mPreferences.getString(PREF_KEY_COMMENT_DAY_2, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_2, mPreferences.getString(PREF_KEY_COMMENT_DAY_1, "")).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_DAY_1, mPreferences.getString(PREF_KEY_COMMENT_CURRENT, "")).apply();

        mPreferences.edit().putInt(PREF_KEY_COLOR_CURRENT, DEFAULT_MOOD).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_CURRENT, "").apply();
    }

    public static int getMood(int position) {

        switch (position) {
            case 6:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_7, DEFAULT_MOOD);
            case 5:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_6, DEFAULT_MOOD);
            case 4:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_5, DEFAULT_MOOD);
            case 3:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_4, DEFAULT_MOOD);
            case 2:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_3, DEFAULT_MOOD);
            case 1:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_2, DEFAULT_MOOD);
            case 0:
                return mPreferences.getInt(PREF_KEY_COLOR_DAY_1, DEFAULT_MOOD);
            default:
                return DEFAULT_MOOD;
        }
    }

    public static String getComment(int position) {

        switch (position) {
            case 6:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_7, "");
            case 5:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_6, "");
            case 4:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_5, "");
            case 3:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_4, "");
            case 2:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_3, "");
            case 1:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_2, "");
            case 0:
                return mPreferences.getString(PREF_KEY_COMMENT_DAY_1, "");
            default:
                return "";
        }
    }

    public static int getCount() {
        int daysPast = mPreferences.getInt(PREF_KEY_DAYS_PAST, 0);

        return daysPast > 7 ? 7 : daysPast;
    }
}
