package com.example.dailymoodtracker.model;

public class Mood {

    private int mImage;
    private int mColor;

    public Mood(int image, int color) {
        setImage(image);
        setColor(color);
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
