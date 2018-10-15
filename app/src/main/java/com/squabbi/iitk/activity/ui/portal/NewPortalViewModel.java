package com.squabbi.iitk.activity.ui.portal;

import android.graphics.Color;

import com.google.android.gms.location.places.Place;
import com.kunzisoft.androidclearchroma.ChromaDialog;
import com.kunzisoft.androidclearchroma.IndicatorMode;
import com.kunzisoft.androidclearchroma.colormode.ColorMode;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel class for the NewPortal Activity. This stores the user's selected Portal colour and
 * portal location details in case of configuration changes. This also passes over the information
 * necessary to create a new portal to the {@link FirebaseRepository}.
 */
public class NewPortalViewModel extends ViewModel {

    private static final String TAG = "NewPortalViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private MutableLiveData<Integer> mColourLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mPortalLocationLiveData = new MutableLiveData<>();
    private MutableLiveData<Place> mPlaceLiveData = new MutableLiveData<>();

    /** Public constructor for ViewModel. Sets the current colour to WHITE */
    public NewPortalViewModel() {
        // Set default colour
        setColourLiveData(Color.WHITE);
    }

    /**
     * Creates a Portal object with the given details and sends it to be added by the Repository.
     * @param name Name of the Portal.
     * @param place Place object of the Portal.
     * @param friendlyLocation String representation of the Portal's location.
     * @param notes Notes about the Portal.
     * @param colour Colour of the Portal as an integer.
     */
    public void addPortal(String name, Place place, String friendlyLocation,
                             String notes, Integer colour) {

        // Create new Portal object
        Portal portal = new Portal(name, place, friendlyLocation, notes, colour);
        // Pass it to the repository to add to Firestore.
        mFirebaseRepository.addPortal(portal);
    }

    /**
     * Builder for the ChromaDialog colour picker.
     * @return ChromaDialog.Builder instance with RGB, HEX and no alpha.
     */
    public ChromaDialog.Builder getChromaDialogBuilder() {

        return new ChromaDialog.Builder()
                .initialColor(Color.GRAY)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX);
    }

    public LiveData<Integer> getColourLiveData() {
        return mColourLiveData;
    }

    public void setColourLiveData(Integer colour) {
        mColourLiveData.setValue(colour);
    }

    public MutableLiveData<String> getPortalLocationLiveData() {
        return mPortalLocationLiveData;
    }

    public void setPortalLocationLiveData(String portalLocation) {
        mPortalLocationLiveData.setValue(portalLocation);
    }

    public MutableLiveData<Place> getPlaceLiveData() {
        return mPlaceLiveData;
    }

    public void setPlaceLiveData(Place place) {
        this.mPlaceLiveData.setValue(place);
    }
}
