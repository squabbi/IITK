package com.squabbi.iitk.activity.ui.inventory.view;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.util.FirebaseRepository;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InventoryViewViewModel extends ViewModel {

    private static final String TAG = "InventoryViewViewModel";

    private FirebaseRepository mRepository = FirebaseRepository.getInstance();
    private String mInventoryPath;
    private String mInventoryId;
    private MutableLiveData<String> mInventoryName = new MutableLiveData<>();
    private ListenerRegistration mInventoryRegistration;
    private DocumentListener mInventoryListener = new DocumentListener();

    private FirestoreRecyclerOptions.Builder<Item> mBaseInventoryItemFirestoreRecyclerBuilder;

    public InventoryViewViewModel(String inventoryPath, String inventoryId) {

        this.mInventoryPath = inventoryPath;
        this.mInventoryId = inventoryId;

        this.mBaseInventoryItemFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Item>()
                .setQuery(mRepository.getInventoryItemDocuments(mInventoryId), Item.class);

        // Create Inventory object from ID, listen to changes to the document
        mInventoryRegistration = mRepository.getDocumentRefObject(mInventoryPath)
                .addSnapshotListener(mInventoryListener);
    }

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

    public FirestoreRecyclerOptions.Builder<Item> getBaseItemFirestoreRecyclerBuilder() {
        return mBaseInventoryItemFirestoreRecyclerBuilder;
    }

    public void addItem(Item item) {
        mRepository.addItemToInventory(mInventoryId, item);
    }

    public LiveData<String> getInventoryName() {
        return mInventoryName;
    }

    public String getInventoryId() {
        return mInventoryId;
    }
}
