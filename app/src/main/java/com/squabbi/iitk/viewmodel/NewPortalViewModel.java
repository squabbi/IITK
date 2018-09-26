package com.squabbi.iitk.viewmodel;

import com.google.android.gms.location.places.Place;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.ViewModel;

public class NewPortalViewModel extends ViewModel {

    private static final String TAG = "NewPortalViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();

    public boolean addPortal(String name, Place place, String friendlyLocation,
                             String notes, String colour) {

        // Create new Portal object
        Portal portal = new Portal(name, place, friendlyLocation, notes, colour);
        return mFirebaseRepository.addPortal(portal);
    }
}
