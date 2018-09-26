package com.squabbi.iitk.viewmodel;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private FirestoreRecyclerOptions.Builder<Portal> mBaseFirestoreRecyclerBuilder;

    public MainActivityViewModel() {
        super();
        mBaseFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Portal>()
                .setQuery(mFirebaseRepository.getPortalDocuments(), Portal.class);
    }

    public FirestoreRecyclerOptions.Builder<Portal> getBaseFirestoreRecyclerBuilder() {
        return mBaseFirestoreRecyclerBuilder;
    }
}
