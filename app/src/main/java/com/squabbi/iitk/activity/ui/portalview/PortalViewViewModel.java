package com.squabbi.iitk.activity.ui.portalview;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.ListenerRegistration;
import com.squabbi.iitk.R;
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
    private MutableLiveData<String> mPortalGeoPointString = new MutableLiveData<>();
    private MutableLiveData<String> mPortalDateCreated = new MutableLiveData<>();
    private MutableLiveData<ColorDrawable> mPortalColourDrawableLiveData = new MutableLiveData<>();

    private String mDocumentPath;
    private ListenerRegistration mRegistration;
    private DocumentListener mListener = new DocumentListener();

    // Constructor
    public PortalViewViewModel(final String documentPath) {
        // Save documentPath
        this.mDocumentPath = documentPath;

        // Create Portal object for ID, listen for changes to the document
        mRegistration = mRepository.getDocumentRefObject(mDocumentPath).addSnapshotListener(mListener);
    }

    private class DocumentListener implements EventListener<DocumentSnapshot> {
        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                // A Firebase error occurred
                e.printStackTrace();
                return;
            }

            if (documentSnapshot != null) {
                Portal portal = documentSnapshot.toObject(Portal.class);

                if (portal != null) {
                    mPortalName.setValue(portal.getName());
                    mPortalFriendlyLocation.setValue(portal.getFriendlyLocation());
                    mPortalNotes.setValue(portal.getNotes());
                    mPortalGeoPoint.setValue(portal.getGeoPoint());

                    if (portal.getGeoPoint() != null) {
                        mPortalGeoPointString.setValue(portal.getGeoPoint().toString());
                    }

                    // For when Portals aren't registered on Firebase before opening
                    if (portal.getCreatedAt() != null) {
                        mPortalDateCreated.setValue(portal.getCreatedAt().toString());
                    } else {
                        mPortalDateCreated.setValue(Resources.getSystem().getString(R.string.detail_portal_createddate_unvailable));
                    }

                    mPortalColourDrawableLiveData.setValue(new ColorDrawable(portal.getColour()));
                }
            }
        }
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

    public LiveData<GeoPoint> getPortalGeoPoint() {
        return mPortalGeoPoint;
    }

    public LiveData<String> getPortalGeoPointString() { return mPortalGeoPointString; }

    public LiveData<String> getCreatedDate() { return mPortalDateCreated; }

    public String getPortalDocumentPath() { return mDocumentPath; }

    public LiveData<ColorDrawable> getPortalColour() {
        return mPortalColourDrawableLiveData;
    }

    public void deletePortal(String documentPath) {

        // Remove listener before removing the document
        mRegistration.remove();
        // Remove document reference
        mRepository.deleteDocument(documentPath);
    }
}
