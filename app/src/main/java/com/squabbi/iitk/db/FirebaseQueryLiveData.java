package com.squabbi.iitk.db;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squabbi.iitk.model.Portal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;

/**
 * LiveData extension that returns a LiveData object of a list of Portals from a Firebase Firestore instance.
 * Currently, this class is no-longer used, as the LiveData will be processed by the Adapter, and the
 * DocumentChanges passed down.
 */
public class FirebaseQueryLiveData extends LiveData<List<Portal>> implements EventListener<QuerySnapshot> {

    private static final String TAG = "FirebaseQueryLiveData";
    private Query mQuery;
    private ListenerRegistration mRegistration;

    private List<Portal> mPortals = new ArrayList<>();

    // Constructor
    public FirebaseQueryLiveData(Query query) {
        this.mQuery = query;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");

        if (mQuery != null && mRegistration == null) {
            // Listen to changes to the Query
            mRegistration = mQuery.addSnapshotListener(this);
        }
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");

        if (mRegistration != null) {
            mRegistration.remove();
            mRegistration = null;
        }

        // Clear array list
        mPortals.clear();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            // TODO: Handle errors
        }

        setValue(toPortals(snapshot));
        // TODO: Make a list of DocumentChanges ??
    }

    private List<Portal> toPortals(QuerySnapshot snapshot) {
        for (DocumentChange documentChange : snapshot.getDocumentChanges()) {

            switch (documentChange.getType()) {
                case ADDED:
                    mPortals.add(documentChange.getNewIndex(), documentChange.getDocument().toObject(Portal.class));
                    break;
                case MODIFIED:
                    if (documentChange.getOldIndex() == documentChange.getNewIndex()) {
                        // Item changed but remained in the same position
                        mPortals.set(documentChange.getOldIndex(), documentChange.getDocument().toObject(Portal.class));
                    } else {
                        // Item changed and changed position
                        mPortals.remove(documentChange.getOldIndex());
                        mPortals.add(documentChange.getNewIndex(), documentChange.getDocument().toObject(Portal.class));
                    }
                    break;
                case REMOVED:
                    mPortals.remove(documentChange.getOldIndex());
                    break;
            }
        }

        return mPortals;
    }
}
