package com.squabbi.iitk.activity.ui.mainlistview;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private FirestoreRecyclerOptions.Builder<Portal> mBasePortalFirestoreRecyclerBuilder;
    private FirestoreRecyclerOptions.Builder<Inventory> mBaseInventoryFirestoreRecyclerBuilder;

    public MainActivityViewModel() {

        mBasePortalFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Portal>()
                .setQuery(mFirebaseRepository.getPortalDocuments(), Portal.class);

        mBaseInventoryFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Inventory>()
                .setQuery(mFirebaseRepository.getInventoryDocuments(), Inventory.class);
    }

    public FirestoreRecyclerOptions.Builder<Portal> getBasePortalFirestoreRecyclerBuilder() {

        return mBasePortalFirestoreRecyclerBuilder;
    }

    public FirestoreRecyclerOptions.Builder<Inventory> getBaseInventoryFirestoreRecyclerBuilder() {

        return mBaseInventoryFirestoreRecyclerBuilder;
    }
}
