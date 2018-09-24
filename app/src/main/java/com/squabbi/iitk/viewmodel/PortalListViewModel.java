package com.squabbi.iitk.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squabbi.iitk.adapter.PortalAdapter;
import com.squabbi.iitk.db.FirebaseQueryLiveData;
import com.squabbi.iitk.util.Constants;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PortalListViewModel extends ViewModel {

    private static final FirebaseAuth FIREBASE_AUTH = FirebaseAuth.getInstance();
    private static final Query AGENT_PORTAL_REF = FirebaseFirestore.getInstance()
            .collection(Constants.COLLECTION_AGENTS)
            .document(FIREBASE_AUTH.getUid())
            .collection(Constants.COLLECTION_PORTALS);

    private FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(AGENT_PORTAL_REF);

    @NonNull
    public LiveData<QuerySnapshot> getQuerySnapshotLiveData() {
        return mLiveData;
    }
}
