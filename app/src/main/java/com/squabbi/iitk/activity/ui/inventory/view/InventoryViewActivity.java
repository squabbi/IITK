package com.squabbi.iitk.activity.ui.inventory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.activity.ui.mainlistview.InventoryListFragment;
import com.squabbi.iitk.util.ViewModelFactory;

public class InventoryViewActivity extends AppCompatActivity implements OnFragmentViewInteractionListener, OnFirestoreItemClickListener {

    private ItemListViewFragment mItemListFragment;
    private ItemViewFragment mItemViewFragment;
    private CapsuleViewFragment mCapsuleViewFragment;

    private InventoryViewViewModel mViewModel;

    public static final String INVENTORY_ID_KEY = "inventory_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);

        // Instantiate fragments
        mItemListFragment = ItemListViewFragment.newInstance();
        mItemViewFragment = ItemViewFragment.newInstance();
        mCapsuleViewFragment = CapsuleViewFragment.newInstance();

        // Register ViewModel with custom constructor
        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(
                getIntent().getStringExtra(InventoryListFragment.INVENTORY_PATH_KEY),
                getIntent().getStringExtra(InventoryListFragment.INVENTORY_ID_KEY)
        )).get(InventoryViewViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.inventory_view_framelayout, mItemListFragment)
                    .commitNow();
        }
    }

    @Override
    public void onViewPressed(View view) {

        switch (view.getId()) {
            case R.id.inventory_item_fab:
                // Launch manage inventory activity
                Intent intent = new Intent(this, ManageInventoryActivity.class);
                intent.putExtra(INVENTORY_ID_KEY, mViewModel.getInventoryId());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onFirestoreItemClick(DocumentSnapshot documentSnapshot, int position) {
        // Handle opening a new fragment for selected item
        Toast.makeText(this, documentSnapshot.getId(), Toast.LENGTH_LONG).show();
    }
}
