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
import com.squabbi.iitk.model.Capsule;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.Portal;

import androidx.annotation.NonNull;

public class FirebaseRepository {

    private static FirebaseRepository sFirebaseRepository;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private Boolean mResult;

    // Constants and finals for various collections
    public static final String COLLECTION_PORTALS = "portals";
    public static final String COLLECTION_INVENTORY = "inventory";
    public static final String COLLECTION_ITEMS = "items";
    public static final String COLLECTION_AGENTS = "agents";

    private CollectionReference mPortalCollectionReference;
    private CollectionReference mInventoryCollectionReference;

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

        // Load up collection references
        mPortalCollectionReference = mFirestore.collection(COLLECTION_AGENTS)
                .document(mAuth.getUid()).collection(COLLECTION_PORTALS);

        mInventoryCollectionReference = mFirestore.collection(COLLECTION_AGENTS)
                .document(mAuth.getUid()).collection(COLLECTION_INVENTORY);
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public FirebaseFirestore getFirestore() {
        return mFirestore;
    }

    public Query getPortalDocuments() {
        // TODO: Apply different cases to allow users to filter portals
        // Example query for A-Z portal names
        return mPortalCollectionReference.orderBy("name", Query.Direction.ASCENDING);
    }

    public Query getInventoryDocuments() {
        // TODO: Apply different cases to allow users to filter inventory
        // Example query for A-Z portal names
        return mInventoryCollectionReference.orderBy("createdAt", Query.Direction.ASCENDING);
    }

    public DocumentReference getDocumentRefObject(String documentPath) {
        return mFirestore.document(documentPath);
    }

    public void addPortal(Portal portal) {
        mPortalCollectionReference.add(portal);
    }

    public void addInventory(Inventory inventory) { mInventoryCollectionReference.add(inventory); }

    public void deleteDocument(String documentPath) {
        mFirestore.document(documentPath).delete();
    }

    public void addItemToInventory(String inventoryId, Item item) {

        mInventoryCollectionReference.document(inventoryId).collection(COLLECTION_ITEMS).add(item);
    }

    public void addItemToCapsule(String inventoryId, String capsuleId, Item item) {

        mInventoryCollectionReference.document(inventoryId).collection(COLLECTION_ITEMS)
                .document(capsuleId)
                .collection(COLLECTION_ITEMS)
                .add(item);
    }
}
