package com.squabbi.iitk.activity.ui.portalview;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PortalViewViewModel extends ViewModel {

    private static final String TAG = "PortalViewViewModel";

    private FirebaseRepository mRepository = FirebaseRepository.getInstance();

    private MutableLiveData<String> mPortalName = new MutableLiveData<>();
    private MutableLiveData<String> mPortalFriendlyLocation = new MutableLiveData<>();
    private MutableLiveData<String> mPortalNotes = new MutableLiveData<>();
    private MutableLiveData<GeoPoint> mPortalGeoPoint = new MutableLiveData<>();

    private String mDocumentPath;

    // Constructor
    public PortalViewViewModel(final String documentPath) {
        // Save documentPath
        this.mDocumentPath = documentPath;

        // Create Portal object for ID, listen for changes to the document
        mRepository.getDocumentRefObject(mDocumentPath).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    Portal portal = documentSnapshot.toObject(Portal.class);

                    mPortalName.setValue(portal.getName());
                    mPortalFriendlyLocation.setValue(portal.getFriendlyLocation());
                    mPortalNotes.setValue(portal.getNotes());
                    mPortalGeoPoint.setValue(portal.getGeoPoint());
                }
            }
        });
    }

    public LiveData<String> getPortalName() {
        return mPortalName;
    }

    public LiveData<String> getPortalFriendlyLocation() {
        return mPortalFriendlyLocation;
    }

    public LiveData<String> getPortalNotes() {
        return mPortalNotes;
    }

    public LiveData<GeoPoint> getPortaGeoPoint() {
        return mPortalGeoPoint;
    }
}
