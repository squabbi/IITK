package com.squabbi.iitk.activity.ui.inventory.view;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.squabbi.iitk.model.FirestoreItem;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.util.FirebaseRepository;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel for Viewing inventory contents, holds inventoryID and provides access to the Query
 * required to populate the recycler view based of the InventoryID.
 */

public class InventoryViewViewModel extends ViewModel {

    private static final String TAG = "InventoryViewViewModel";

    private FirebaseRepository mRepository = FirebaseRepository.getInstance();
    private String mInventoryPath;
    private String mInventoryId;
    private MutableLiveData<String> mInventoryName = new MutableLiveData<>();
    private ListenerRegistration mInventoryRegistration;
    private DocumentListener mInventoryListener = new DocumentListener();

    private FirestoreRecyclerOptions.Builder<FirestoreItem> mBaseInventoryItemFirestoreRecyclerBuilder;

    /**
     * Constructor for InventoryViewViewModel, requires to be constructed via a ViewModel Factory.
     * Takes in an Firestore document path and ID acting as the inventory which will be viewed.
     * @param inventoryPath Path to the Inventory document.
     * @param inventoryId ID of the Inventory document.
     */
    public InventoryViewViewModel(String inventoryPath, String inventoryId) {

        // Assign inventory details
        this.mInventoryPath = inventoryPath;
        this.mInventoryId = inventoryId;

        this.mBaseInventoryItemFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<FirestoreItem>()
                .setQuery(mRepository.getInventoryItemDocuments(mInventoryId), FirestoreItem.class);

        // Create Inventory object from ID, listen to changes to the document
        mInventoryRegistration = mRepository.getDocumentRefObject(mInventoryPath)
                .addSnapshotListener(mInventoryListener);
    }

    /**
     * Overridden DocumentListener class to set the title of the Toolbar as the currently selected
     * Inventory name.
     */
    private class DocumentListener implements EventListener<DocumentSnapshot> {

        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

            if (e != null) {
                // A Firebase error occurred
                e.printStackTrace();
                return;
            }

            if (documentSnapshot != null) {
                Inventory inventory = documentSnapshot.toObject(Inventory.class);

                if (inventory != null) {
                    mInventoryName.setValue(inventory.getName());
                }
            }
        }
    }

    /**
     * Returns pre-filled FirestoreRecyclerOptions with the Query preset to be built by the
     * Recycler initaliser.
     * @return preset FirestoreOptionsBuilder with Inventory Query.
     */
    public FirestoreRecyclerOptions.Builder<FirestoreItem> getBaseItemFirestoreRecyclerBuilder() {
        return mBaseInventoryItemFirestoreRecyclerBuilder;
    }

    public LiveData<String> getInventoryName() {
        return mInventoryName;
    }

    public String getInventoryId() {
        return mInventoryId;
    }
}
