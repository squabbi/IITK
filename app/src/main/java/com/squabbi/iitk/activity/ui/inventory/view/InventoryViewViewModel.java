package com.squabbi.iitk.activity.ui.inventory.view;

import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.ViewModel;

public class InventoryViewViewModel extends ViewModel {

    private String mInventoryId;

    private MutableLiveData<>

    private FirebaseRepository mRepository = FirebaseRepository.getInstance();

    public void addItem(Item item) {

        mRepository.addItemToInventory(, )
    }
}
