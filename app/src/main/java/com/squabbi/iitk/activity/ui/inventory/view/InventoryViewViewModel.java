package com.squabbi.iitk.activity.ui.inventory.view;

import com.google.firebase.firestore.ListenerRegistration;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InventoryViewViewModel extends ViewModel {

    private static final String TAG = "InventoryViewViewModel";

    private FirebaseRepository mRepository = FirebaseRepository.getInstance();
    private String mInventoryId;
    private MutableLiveData<String> mInventoryName = new MutableLiveData<>();
    private ListenerRegistration mRegistration;

    public InventoryViewViewModel(String inventoryId) {

        this.mInventoryId = inventoryId;

        // Get name of inventory
        mInventoryName.setValue(mRepository.getInventoryName(mInventoryId));
    }

    public void addItem(Item item) {

    }

    public LiveData<String> getInventoryName() {

        return mInventoryName;
    }
}
