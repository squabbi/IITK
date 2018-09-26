package com.squabbi.iitk.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirebaseRepository {

    private static FirebaseRepository sFirebaseRepository;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    // Constants and finals for various collections
    private static final String COLLECTION_PORTALS= "portals";
    private static final String COLLECTION_AGENTS = "agents";
    private CollectionReference mPortalCollectionReference;

    public static FirebaseRepository getInstance() {
        if (sFirebaseRepository == null) {
            sFirebaseRepository = new FirebaseRepository();
        }

        return sFirebaseRepository;
    }

    private FirebaseRepository() {
        // Initalise Firebase and Firestore instances
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        mPortalCollectionReference = mFirestore.collection(COLLECTION_AGENTS)
                .document(mAuth.getUid()).collection(COLLECTION_PORTALS);
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public FirebaseFirestore getFirestore() {
        return mFirestore;
    }

    public Query getPortalDocuments() {
        // TODO: Apply different cases to allow people to filter portals
        // Example query for A-Z portal names
        return mPortalCollectionReference.orderBy("name", Query.Direction.ASCENDING);
    }
}
