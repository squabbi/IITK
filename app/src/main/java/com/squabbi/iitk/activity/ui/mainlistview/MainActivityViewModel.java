package com.squabbi.iitk.activity.ui.mainlistview;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.FirebaseRepository;

import androidx.lifecycle.ViewModel;

/**
 * ViewModel for MainActivity. This ViewModel mostly contains getter methods to access the
 * Firestore Repository's Queries to be used for constructing the builder options for the
 * various Recycler views.
 */
public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private FirebaseRepository mFirebaseRepository = FirebaseRepository.getInstance();
    private FirestoreRecyclerOptions.Builder<Portal> mBasePortalFirestoreRecyclerBuilder;
    private FirestoreRecyclerOptions.Builder<Inventory> mBaseInventoryFirestoreRecyclerBuilder;

    /**
     * Public constructor for the ViewModel. Instantiates both the Portal and Inventory
     * FirestoreRecycler builder options for use in each fragment.
     */
    public MainActivityViewModel() {

        mBasePortalFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Portal>()
                .setQuery(mFirebaseRepository.getPortalDocuments(), Portal.class);

        mBaseInventoryFirestoreRecyclerBuilder = new FirestoreRecyclerOptions.Builder<Inventory>()
                .setQuery(mFirebaseRepository.getInventoryDocuments(), Inventory.class);
    }

    /**
     * Returns FirestoreRecyclerOptions for getting the current user's portals.
     * @return FirestoreRecyclerOptions.Builder for Portals.
     */
    public FirestoreRecyclerOptions.Builder<Portal> getBasePortalFirestoreRecyclerBuilder() {

        return mBasePortalFirestoreRecyclerBuilder;
    }

    /**
     * Returns FirestoreRecyclerOptions for getting the current user's inventories.
     * @return FirestoreRecyclerOptions.Builder for Invnetories.
     */
    public FirestoreRecyclerOptions.Builder<Inventory> getBaseInventoryFirestoreRecyclerBuilder() {

        return mBaseInventoryFirestoreRecyclerBuilder;
    }

    /**
     * Passes on the desired attributes of the new inventory to the repository for creation.
     * @param name Name of the Inventory.
     * @param description Description of the Inventory.
     * @param colour Colour accent of the Inventory.
     */
    public void addInventory(String name, String description, Integer colour) {

        mFirebaseRepository.addInventory(new Inventory(name, description, colour));
    }
}
