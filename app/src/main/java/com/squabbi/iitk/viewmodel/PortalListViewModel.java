package com.squabbi.iitk.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squabbi.iitk.db.FirebaseDocChangeLiveData;
import com.squabbi.iitk.util.Constants;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PortalListViewModel extends ViewModel {

    private static final FirebaseAuth FIREBASE_AUTH = FirebaseAuth.getInstance();

    private static Query AGENT_PORTAL_REF = FirebaseFirestore.getInstance()
            .collection(Constants.COLLECTION_AGENTS)
            .document(FIREBASE_AUTH.getUid())
            .collection(Constants.COLLECTION_PORTALS);

    private FirebaseDocChangeLiveData mFirebaseDocChangeLiveData = new FirebaseDocChangeLiveData(AGENT_PORTAL_REF);

    public LiveData<List<DocumentChange>> getDocChangesLiveData() {

        return mFirebaseDocChangeLiveData;
    }
}
