package com.squabbi.iitk.util;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squabbi.iitk.model.Portal;

import androidx.lifecycle.LiveData;

public class PortalRepository {
    // Firebase related stuff here?
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    private LiveData<Portal> mPortalLiveData;

    public PortalRepository() {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }

    public boolean addPortal(Portal portal) {
        if (mAuth.getCurrentUser() != null) {
            return mFirestore.collection(Constants.COLLECTION_AGENTS)
                    .document(mAuth.getUid())
                    .collection(Constants.COLLECTION_PORTALS)
                    .add(portal).isSuccessful();
        }

        return false;
    }
}
