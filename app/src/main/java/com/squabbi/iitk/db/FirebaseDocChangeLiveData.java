package com.squabbi.iitk.db;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squabbi.iitk.adapter.PortalAdapter;

import java.util.List;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;

/**
 * Extension class of LiveData<T> which listens to changes of the Query passed in, and returns document changes.
 * This class is exclusive for Portal List as we have implemented an onInactive method to clear the list of
 * DocumentChanges in the PortalAdapter.
 */
public class FirebaseDocChangeLiveData extends LiveData<List<DocumentSnapshot>> implements EventListener<QuerySnapshot> {

    private static final String TAG = "FirebaseDocChangeLvDta";
    private Query mQuery;
    private ListenerRegistration mRegistration;

    // Constructor
    public FirebaseDocChangeLiveData(Query query) {
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
        //PortalAdapter.clearSnapshots();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            // TODO: Handle errors
            Log.w(TAG, "onEvent:error", e);
            return;
        }

        // Set the value of LiveData
        //setValue(querySnapshot.getDocumentChanges());
        // Temporary, send all documents, not just changes.
        setValue(querySnapshot.getDocuments());
    }
}
