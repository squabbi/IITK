package com.squabbi.iitk.activity.ui.inventory.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.activity.ui.inventory.view.categories.ItemAllFragment;
import com.squabbi.iitk.activity.ui.mainlistview.InventoryListFragment;
import com.squabbi.iitk.util.ViewModelFactory;

import static com.squabbi.iitk.util.Constants.INVENTORY_ID_KEY;
import static com.squabbi.iitk.util.Constants.INVENTORY_PATH_KEY;

/**
 * The activity opens the selected inventory and displays the items within the inventory.
 * This activity implements {@link OnFirestoreItemClickListener}, {@link OnFragmentViewInteractionListener}
 * and {@link NavigationView.OnNavigationItemSelectedListener}.
 */

public class InventoryViewActivity extends AppCompatActivity implements OnFragmentViewInteractionListener,
        OnFirestoreItemClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private ItemViewFragment mItemViewFragment;
    private CapsuleViewFragment mCapsuleViewFragment;

    // Page fragments
    private ItemAllFragment mItemAllFragment;

    private InventoryViewViewModel mViewModel;

    @BindView(R.id.inventory_items_drawerlayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.inventory_items_navigationview)
    NavigationView mNavigationView;

    @BindView(R.id.inventory_items_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inventory_view);
        ButterKnife.bind(this);

        // Register ViewModel with custom constructor
        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(
                getIntent().getStringExtra(INVENTORY_PATH_KEY),
                getIntent().getStringExtra(INVENTORY_ID_KEY)
        )).get(InventoryViewViewModel.class);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        // Instantiate fragments
        mItemAllFragment = ItemAllFragment.newInstance();

        mItemViewFragment = ItemViewFragment.newInstance();
        mCapsuleViewFragment = CapsuleViewFragment.newInstance();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            // TODO: allow the user to set default page
            MenuItem item = mNavigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation drawer selections here
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_inv_all:
                updateFragment(mItemAllFragment);
                break;
        }

        setTitle(item.getTitle());
        item.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                toggleNavigationDrawer();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleNavigationDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void updateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.inventory_items_framelayout, fragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Check if navigation drawer is opened
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close it
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.inventory_items_fab)
    void onFabClick() {
        // Launch manage inventory activity
        Intent intent = new Intent(this, ManageInventoryActivity.class);
        intent.putExtra(INVENTORY_ID_KEY, mViewModel.getInventoryId());
        startActivity(intent);
    }

    @Override
    public void onViewPressed(View view) {

    }

    @Override
    public void onMenuItemPressed(MenuItem menuItem) {

    }

    @Override
    public void onFirestoreItemClick(DocumentSnapshot documentSnapshot, @Nullable Integer position) {
        // Handle opening a new fragment for selected item
        Toast.makeText(this, documentSnapshot.getId(), Toast.LENGTH_LONG).show();
    }
}
