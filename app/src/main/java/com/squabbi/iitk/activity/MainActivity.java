package com.squabbi.iitk.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squabbi.iitk.R;
import com.squabbi.iitk.fragment.InventoryFragment;
import com.squabbi.iitk.fragment.PortalListFragment;
import com.squabbi.iitk.model.Inventory;
import com.squabbi.iitk.service.MyHoverMenuService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.main_activity_toolbar) Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    // Fragments
    private InventoryFragment mInventoryFragment;
    private PortalListFragment mPortalListFragment;

    // Firebase authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if the user is signed in (non-null) and update the UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Firebase auth
        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // assign fragments
        mInventoryFragment = new InventoryFragment();
        mPortalListFragment = new PortalListFragment();

        mNavigationView.setNavigationItemSelectedListener(this);

        // Set the current fragment as the Portal list view
//        updateFragment(mPortalListFragment);
//        mNavigationView.setCheckedItem(R.id.nav_portals);
//        setTitle(R.string.fragment_portals);

        if (savedInstanceState == null) {
            MenuItem item = mNavigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state after onRestoreInstanceState has occurred
        mDrawerToggle.syncState();
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

    private void updateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }
}
