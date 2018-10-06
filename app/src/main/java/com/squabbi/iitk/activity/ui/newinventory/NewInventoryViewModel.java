package com.squabbi.iitk.activity.ui.newinventory;

import android.graphics.Color;

import com.kunzisoft.androidclearchroma.ChromaDialog;
import com.kunzisoft.androidclearchroma.IndicatorMode;
import com.kunzisoft.androidclearchroma.colormode.ColorMode;
import com.squabbi.iitk.model.Capsule;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.KeyLocker;
import com.squabbi.iitk.util.Constants;
import com.squabbi.iitk.util.FirebaseRepository;

import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewInventoryViewModel extends ViewModel {

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private MutableLiveData<boolean[]> mSelectedCapsulesLiveData = new MutableLiveData<>();
    private boolean[] mSelectedCapsules = new boolean[6];
    private MutableLiveData<Integer> mColorLiveData = new MutableLiveData<>();

    public NewInventoryViewModel() {

        // Set default colour
        setColorLiveData(Color.WHITE);
    }

    public void addInventory(String name, String description) {

        Constants.LockerType[] lockerTypes = Constants.LockerType.values();

        // Generate items list
        List<Item> items = new LinkedList<>();
        for (int i = 0; i < mSelectedCapsules.length; i++) {
            if (mSelectedCapsules[i]) {
                items.add(new KeyLocker(lockerTypes[i], String.valueOf(i)));
            }
        }

//        // Create inventory object
//        Inventory inventory = new Inventory(name, description,
//                getColourLiveData().getValue(), items);
//        mFirebaseRepository.addInventory(inventory);
    }

    public ChromaDialog.Builder getChromaDialogBuilder() {

        return new ChromaDialog.Builder()
                .initialColor(Color.GRAY)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX);
    }

    public void setSelectedCapsule(int capsule, boolean state) {

        mSelectedCapsules[capsule] = state;
        // Update the LiveData
        mSelectedCapsulesLiveData.setValue(mSelectedCapsules);
    }

    public boolean getSelectedCapsule(int capsule) {
        return mSelectedCapsules[capsule];
    }

    public LiveData<boolean[]> getSelectedCapsules() {
        return mSelectedCapsulesLiveData;
    }

    public LiveData<Integer> getColourLiveData() { return mColorLiveData; }

    public void setColorLiveData(Integer colour) { mColorLiveData.setValue(colour); }


}