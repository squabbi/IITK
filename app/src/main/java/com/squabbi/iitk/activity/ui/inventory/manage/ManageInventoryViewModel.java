package com.squabbi.iitk.activity.ui.inventory.manage;

import android.graphics.Color;

import com.kunzisoft.androidclearchroma.ChromaDialog;
import com.kunzisoft.androidclearchroma.IndicatorMode;
import com.kunzisoft.androidclearchroma.colormode.ColorMode;
import com.squabbi.iitk.model.InventoryItem;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.util.FirebaseRepository;
import com.squabbi.iitk.util.InventoryItemConverter;

import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageInventoryViewModel extends ViewModel {

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private MutableLiveData<Integer> mColorLiveData = new MutableLiveData<>();

    private MutableLiveData<List<InventoryItem>> mInventoryCheckoutLiveData = new MutableLiveData<>();
    private List<InventoryItem> mInventoryCartItems;
    private String mInventoryId;

    public ManageInventoryViewModel(String inventoryId) {

        this.mInventoryCartItems = new LinkedList<>();
        this.mInventoryId = inventoryId;
    }

    public List<InventoryItem> getInventoryCartItems() {
        return mInventoryCartItems;
    }

    public MutableLiveData<List<InventoryItem>> getInventoryCheckoutLiveData() {
        return mInventoryCheckoutLiveData;
    }

    public void addItemToInventoryCart(InventoryItem item) {
        mInventoryCartItems.add(item);
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    public void removeItemFromInventoryCart(int position) {
        mInventoryCartItems.remove(position);
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    public void removeAllFromCart() {
        mInventoryCartItems.clear();
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    public void checkoutCart() {
        // Iterate through InventoryCartItems
        for (InventoryItem item : mInventoryCartItems) {
            // Determine a respective Item object
            mFirebaseRepository.addItemToInventory(mInventoryId, InventoryItemConverter.determineItem(item));
        }
    }

    public void addItemsToInventory(List<Item> items) {

        // TODO: DO THIS SORTING IN THE REPOSITORY ??
        for (Item item : items) {
            mFirebaseRepository.addItemToInventory(mInventoryId, item);
        }
    }

    public ChromaDialog.Builder getChromaDialogBuilder() {

        return new ChromaDialog.Builder()
                .initialColor(Color.GRAY)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX);
    }

    public LiveData<Integer> getColourLiveData() { return mColorLiveData; }

    public void setColorLiveData(Integer colour) { mColorLiveData.setValue(colour); }
}