package com.squabbi.iitk.activity.ui.inventory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.mainlistview.InventoryListFragment;
import com.squabbi.iitk.util.ViewModelFactory;

public class InventoryViewActivity extends AppCompatActivity implements InventoryItemListFragment.OnFragmentInteractionListener {

    private InventoryItemListFragment mItemListFragment;
    private ItemViewFragment mItemViewFragment;
    private CapsuleViewFragment mCapsuleViewFragment;

    private InventoryViewViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);

        // Instantiate fragments
        mItemListFragment = InventoryItemListFragment.newInstance();
        mItemViewFragment = ItemViewFragment.newInstance();
        mCapsuleViewFragment = CapsuleViewFragment.newInstance();

        // Register ViewModel with custom constructor
        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(
                getIntent().getStringExtra(InventoryListFragment.INVENTORY_REFERENCE_KEY)
        )).get(InventoryViewViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.inventory_view_framelayout, mItemListFragment)
                    .commitNow();
        }
    }

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot, int position) {
        // Handle opening a new fragment for selected item
    }
}
