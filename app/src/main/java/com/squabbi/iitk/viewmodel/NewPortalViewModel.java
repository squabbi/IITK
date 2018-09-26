package com.squabbi.iitk.viewmodel;

import android.graphics.Color;

import com.google.android.gms.location.places.Place;
import com.kunzisoft.androidclearchroma.ChromaDialog;
import com.kunzisoft.androidclearchroma.IndicatorMode;
import com.kunzisoft.androidclearchroma.colormode.ColorMode;
import com.kunzisoft.androidclearchroma.listener.OnColorChangedListener;
import com.kunzisoft.androidclearchroma.listener.OnColorSelectedListener;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewPortalViewModel extends ViewModel {

    private static final String TAG = "NewPortalViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private MutableLiveData<Integer> mColourLiveData;

    public NewPortalViewModel() {
        // Set default colour
        setColourLiveData(Color.WHITE);
    }

    public boolean addPortal(String name, Place place, String friendlyLocation,
                             String notes, Integer colour) {

        // Create new Portal object
        Portal portal = new Portal(name, place, friendlyLocation, notes, colour);
        return mFirebaseRepository.addPortal(portal);
    }

    public ChromaDialog.Builder getChromaDialogBuilder() {
        return new ChromaDialog.Builder()
                .initialColor(Color.GRAY)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX);
    }

    public LiveData<Integer> getColourLiveData() {
        if (mColourLiveData == null) {
            mColourLiveData = new MutableLiveData<>();
        }
        return mColourLiveData;
    }

    public void setColourLiveData(Integer colour) {
        if (mColourLiveData == null) {
            mColourLiveData = new MutableLiveData<>();
        }
        mColourLiveData.setValue(colour);
    }
}
