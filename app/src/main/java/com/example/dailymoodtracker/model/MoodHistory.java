package com.example.dailymoodtracker.model;

import java.util.ArrayList;
import java.util.List;

public class MoodHistory {

    private static Mood[] mMoods = new Mood[7];

    public static Mood[] getMoods() {
        return mMoods;
    }

    public static void setMoods(Mood[] moods) {
        mMoods = moods;
    }

    public static Mood getMood(int position) {
        return mMoods[position];
    }

    public static void addMood(Mood mood) {
        for (int m = mMoods.length - 1; m > 0 ; m--) {
            mMoods[m] = mMoods[m - 1];
        }

        mMoods[0] = mood;
    }

    public static int getCount() {
        int counter = 0;

        for (int i = 0; i < mMoods.length && mMoods[i] != null; i++) {
            counter++;
        }

        return counter;
    }
}
