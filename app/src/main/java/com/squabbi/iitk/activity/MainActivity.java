package com.squabbi.iitk.activity;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squabbi.iitk.R;
import com.squabbi.iitk.fragment.InventoryFragment;
import com.squabbi.iitk.fragment.PortalListFragment;
import com.squabbi.iitk.viewmodel.MainActivityViewModel;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * This sets the content of the main activity with a app bar, navigation drawer,
 * and sets up the respective listeners and onOptionsItemSelected to handle the
 * changes from the user interacting with the navigation drawer.
 *
 * This is where the fragments are changed programmatically.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Butter Knife binding for the navigation drawer and toolbar
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.main_activity_appbar) BottomAppBar mBottomAppBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private MainActivityViewModel mMainActivityViewModel;

    // Fragments
    private PortalListFragment mPortalListFragment;
    private InventoryFragment mInventoryFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup and reference ViewModel
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setSupportActionBar(mBottomAppBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // assign fragments
        mPortalListFragment = new PortalListFragment();
        mInventoryFragment = new InventoryFragment();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            // TODO: allow the user to set default page
            MenuItem item = mNavigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    @OnClick(R.id.main_fab)
    public void onClick(View view) {
        MenuItem item = mNavigationView.getCheckedItem();
        switch (item.getItemId()) {
            case  R.id.nav_portals:
                startActivity(new Intent(this, NewPortalActivity.class));
                break;
            case R.id.nav_inventory:
                startActivity(new Intent(this, NewInventoryActivity.class));
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration changes to the drawerToggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation drawer selections here
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_portals:
                updateFragment(mPortalListFragment);
                break;
            case R.id.nav_inventory:
                updateFragment(mInventoryFragment);
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
            case R.id.menu_open_intel:
                openIntelMap(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openIntelMap(int intelType) {

    }

    private void updateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }

    public MainActivityViewModel getMainActivityViewModel() {
        return getMainActivityViewModel();
    }
}
