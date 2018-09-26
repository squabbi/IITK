package com.squabbi.iitk.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squabbi.iitk.util.Constants;

import java.util.List;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private static final FirebaseAuth FIREBASE_AUTH = FirebaseAuth.getInstance();

    private static Query AGENT_PORTAL_REF = FirebaseFirestore.getInstance()
            .collection(Constants.COLLECTION_AGENTS)
            .document(FIREBASE_AUTH.getUid())
            .collection(Constants.COLLECTION_PORTALS);

    private CollectionReference PortalCollection = FirebaseFirestore.getInstance()
            .collection(Constants.COLLECTION_AGENTS).document(FIREBASE_AUTH.getUid())
            .collection(Constants.COLLECTION_PORTALS);

    private MutableLiveData<List<DocumentSnapshot>> mListMutableLiveData;

    public LiveData<List<DocumentSnapshot>> getPortalDocumentChange() {
        if (mListMutableLiveData == null) {
            // Create a new list
            mListMutableLiveData = new MutableLiveData<>();
            loadPortalsFromFirestore();
        }

        return mListMutableLiveData;
    }

    private void loadPortalsFromFirestore() {
        // TODO: Move into other class
//        AGENT_PORTAL_REF.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w(TAG, "FirebaseFirestore e::" + e.toString());
//                }
//                mListMutableLiveData.postValue(documentSnapshots.getDocumentChanges());
//            }
//        });

        PortalCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                mListMutableLiveData.postValue(documentSnapshots.getDocuments());
            }
        });
    }
}
