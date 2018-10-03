package com.squabbi.iitk.activity.ui.newinventory;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

public class NewInventoryViewModel extends ViewModel {

    private boolean[] mSelectedCapsules = new boolean[6];
    private Color mColor;

    public void setSelectedCapsule(int capsule) {

        mSelectedCapsules[capsule] = true;
    }

    public boolean getSelectedCapsule(int capsule) {
        
        return mSelectedCapsules[capsule];
    }
}