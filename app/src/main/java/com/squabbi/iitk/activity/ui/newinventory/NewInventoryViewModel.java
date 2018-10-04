package com.squabbi.iitk.activity.ui.newinventory;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewInventoryViewModel extends ViewModel {

    private MutableLiveData<boolean[]> mSelectedCapsulesLiveData = new MutableLiveData<>();
    private boolean[] mSelectedCapsules = new boolean[6];
    private Color mColor;

    public void setSelectedCapsule(int capsule, boolean state) {

        mSelectedCapsules[capsule] = state;
        // Update the LiveData
        mSelectedCapsulesLiveData.setValue(mSelectedCapsules);
    }

    public boolean getSelectedCapsule(int capsule) {

        return mSelectedCapsules[capsule];
    }

    public LiveData<boolean[]> getSelectedCapsules() {

        return mSelectedCapsulesLiveData;
    }
}