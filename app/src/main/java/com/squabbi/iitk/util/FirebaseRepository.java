package com.squabbi.iitk.util;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.squabbi.iitk.model.Portal;

import androidx.annotation.NonNull;

public class FirebaseRepository {

    private static FirebaseRepository sFirebaseRepository;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private Boolean mResult;

    // Constants and finals for various collections
    public static final String COLLECTION_PORTALS= "portals";
    public static final String COLLECTION_AGENTS = "agents";
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

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();

        mFirestore.setFirestoreSettings(settings);

        // Load up collection reference of all portals
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

    public DocumentSnapshot getDocumentObject(String documentPath) {
        return mFirestore.document(documentPath).get().getResult();
    }

    public DocumentReference getDocumentRefObject(String documentPath) {
        return mFirestore.document(documentPath);
    }

    public void addPortal(Portal portal) {
        mPortalCollectionReference.add(portal);
    }

    public void deleteDocument(String documentPath) {
        mFirestore.document(documentPath).delete();
    }

    private void setResult(boolean result) {
        this.mResult = result;
    }

    private boolean getResult() {
        return mResult;
    }

    private void clearResult() {
        mResult = null;
    }
}
