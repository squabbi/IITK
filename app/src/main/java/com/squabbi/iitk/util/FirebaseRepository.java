package com.squabbi.iitk.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.Portal;

/**
 * Singleton repository class/helper for interacting with Firebase backends, including Firebase Auth and
 * Firebase Firestore. This provides the methods for accessing the database and authentication
 * that is used by IITK.
 */

public class FirebaseRepository {

    private static FirebaseRepository sFirebaseRepository;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private Boolean mResult;

    // Constants and finals for various collections
    public static final String COLLECTION_PORTALS = "portals";
    public static final String COLLECTION_ITEMS = "items";
    public static final String COLLECTION_INVENTORY = "inventory";
    public static final String COLLECTION_AGENTS = "agents";

    public static final String FIELD_NAME = "name";

    private CollectionReference mPortalCollectionReference;
    private CollectionReference mInventoryCollectionReference;

    /** Singleton instance getter for this repository */
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

    /**
     * Returns a query comprised of all Portals within the current user's document, sorted by name
     * and in ascending order.
     * @return Query of portals.
     */
    public Query getPortalDocuments() {
        // TODO: Apply different cases to allow users to filter portals
        // Example query for A-Z portal names
        return mPortalCollectionReference.orderBy("name", Query.Direction.ASCENDING);
    }

    /**
     * Returns a query comprised of all Inventories within the current user's document, sorted by
     * creation date and in ascending order.
     * @return Query of inventories.
     */
    public Query getInventoryDocuments() {
        // TODO: Apply different cases to allow users to filter inventory
        // Example query for A-Z portal names
        return mInventoryCollectionReference.orderBy("createdAt", Query.Direction.ASCENDING);
    }

    /**
     * Returns a query of all inventory items within a specific inventory.
     * @param inventoryId The ID of the inventory to retrieve items from.
     * @return Query of the documents wihin the inventory collection.
     */
    public Query getInventoryItemDocuments(String inventoryId){
        // TODO: Apply different cases to allow users to filter inventory
        // Example by type??
        return mInventoryCollectionReference.document(inventoryId).collection(COLLECTION_ITEMS);
    }

    /**
     * Returns a DocumentReference of the document found at the document path.
     * @param documentPath Location of the document to return.
     * @return Returns document found at the documentPath.
     */
    public DocumentReference getDocumentRefObject(String documentPath) {
        return mFirestore.document(documentPath);
    }

    /**
     * Adds a portal to the current user's Portal collection.
     * @param portal Portal item to be added.
     */
    public void addPortal(Portal portal) {
        mPortalCollectionReference.add(portal);
    }

    /**
     * Adds a new inventory to the current user's inventory collection.
     * @param inventory Inventory to be added.
     */
    public void addInventory(Inventory inventory) { mInventoryCollectionReference.add(inventory); }

    /**
     * Deletes document found at the provided document path.
     * @param documentPath The path to the document to be deleted.
     */
    public void deleteDocument(String documentPath) {
        mFirestore.document(documentPath).delete();
    }

    /**
     * Adding items to a specific inventory of the current user.
     * @param inventoryId Inventory to add items to.
     * @param item Item to add to the inventory.
     */
    public void addItemToInventory(String inventoryId, Item item) {

        mInventoryCollectionReference.document(inventoryId).collection(COLLECTION_ITEMS).add(item);
    }

    /**
     * Add item to a specific capsule, within a specific inventory.
     * @param inventoryId Inventory of the capsule.
     * @param capsuleId ID of the capsule to add items to.
     * @param item Item to be added to the capsule.
     */
    public void addItemToCapsule(String inventoryId, String capsuleId, Item item) {

        mInventoryCollectionReference.document(inventoryId).collection(COLLECTION_ITEMS)
                .document(capsuleId)
                .collection(COLLECTION_ITEMS)
                .add(item);
    }
}
