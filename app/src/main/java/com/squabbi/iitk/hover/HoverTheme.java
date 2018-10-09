package com.squabbi.iitk.hover;

import androidx.annotation.ColorInt;

public class HoverTheme {

    @ColorInt
    private int mAccentColour;

    @ColorInt
    private int mBaseColour;

    public HoverTheme(@ColorInt int accentColour, @ColorInt int baseColour) {
        mAccentColour = accentColour;
        mBaseColour = baseColour;
    }

    @ColorInt
    public int getAccentColour() {
        return mAccentColour;
    }

    @ColorInt
    public int getBaseColour() {
        return mBaseColour;
    }
}
