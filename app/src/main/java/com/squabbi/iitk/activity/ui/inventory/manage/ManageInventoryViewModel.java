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

/**
 * The ViewModel for ManageInventory activity and related fragments. This stores temporary view data
 * so that data is persisted through configuration changes.
 *
 * This ViewModel requires an InventoryID to be passed in, to retrieve the data of the inventory via
 * Firestore.
 */
public class ManageInventoryViewModel extends ViewModel {

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private MutableLiveData<Integer> mColorLiveData = new MutableLiveData<>();

    private MutableLiveData<List<InventoryItem>> mInventoryCheckoutLiveData = new MutableLiveData<>();
    private List<InventoryItem> mInventoryCartItems;
    private String mInventoryId;

    /**
     * Public constructor for the ViewModel which requires an InventoryID string.
     * @param inventoryId documentID for the Inventory to manage.
     */
    public ManageInventoryViewModel(String inventoryId) {

        this.mInventoryCartItems = new LinkedList<>();
        this.mInventoryId = inventoryId;
    }

    /** Returns list of InventoryItems in the current cart */
    public List<InventoryItem> getInventoryCartItems() {
        return mInventoryCartItems;
    }

    /** Returns the live data of the list of InventoryItems in the cart */
    public MutableLiveData<List<InventoryItem>> getInventoryCheckoutLiveData() {
        return mInventoryCheckoutLiveData;
    }

    /**
     * Adds an InventoryItem to the current cart.
     * @param item InventoryItem to add to the cart.
     */
    public void addItemToInventoryCart(InventoryItem item) {
        mInventoryCartItems.add(item);
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    /**
     * Removes the item at the provided position from the inventory cart.
     * @param position Postition of the item to remove from the cart list.
     */
    public void removeItemFromInventoryCart(int position) {
        mInventoryCartItems.remove(position);
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    /** Removes all item from the cart */
    public void removeAllFromCart() {
        mInventoryCartItems.clear();
        mInventoryCheckoutLiveData.setValue(mInventoryCartItems);
    }

    /** Adds all items from the item cart to the current Inventory on Firestore */
    public void checkoutCart() {
        // Iterate through InventoryCartItems
        for (InventoryItem item : mInventoryCartItems) {
            // Determine a respective Item object
            mFirebaseRepository.addItemToInventory(mInventoryId, InventoryItemConverter.determineItem(item));
        }
    }

    /** ChromaDialog builder for colour picking */
    public ChromaDialog.Builder getChromaDialogBuilder() {

        return new ChromaDialog.Builder()
                .initialColor(Color.GRAY)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX);
    }

    public LiveData<Integer> getColourLiveData() { return mColorLiveData; }

    public void setColorLiveData(Integer colour) { mColorLiveData.setValue(colour); }
}