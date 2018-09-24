package com.squabbi.iitk.db;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;

public class FirebaseQueryLiveData extends LiveData<QuerySnapshot> {

    private static final String TAG = "FirebaseQueryLiveData";
    private Query mQuery;
    private final MyValueEventListener mListener = new MyValueEventListener();
    private ListenerRegistration mRegistration;

    public FirebaseQueryLiveData(Query query) {
        this.mQuery = query;
    }

    @Override
    protected void onActive() {
        super.onActive();
        
        Log.d(TAG, "onActive");
        if (mRegistration == null) {
            mRegistration = mQuery.addSnapshotListener(mListener);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();

        Log.d(TAG, "onInActive");
        if (mRegistration != null) {
            mRegistration.remove();
        }
    }

    private class MyValueEventListener implements EventListener<QuerySnapshot> {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            // Handle errors
            if (e != null) {
                return;
            }

            setValue(queryDocumentSnapshots);


//            // Dispatch the event
//            for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
//                // Snapshot of the changed document
//                DocumentSnapshot snapshot = change.getDocument();
//
//                switch (change.getType()) {
//                    case ADDED:
//                        onDocumentAdded(change);
//                        break;
//                    case MODIFIED:
//                        onDocumentModified(change);
//                        break;
//                    case REMOVED:
//                        onDocumentRemoved(change);
//                        break;
//                }
//            }
        }
    }
}
