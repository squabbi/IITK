package com.squabbi.iitk.activity;

import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squabbi.iitk.R;
import com.squabbi.iitk.fragment.InventoryFragment;
import com.squabbi.iitk.fragment.PortalListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        // Print welcome message
        if (currentUser != null) {
            Snackbar.make(findViewById(R.id.main_cordlayout),
                    getString(R.string.main_welcome_back, currentUser.getDisplayName()),
                    Snackbar.LENGTH_SHORT).show();
        }

        TextView textView = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_current_user_tv);
        textView.setText(mAuth.getCurrentUser().getUid());

        //updateUI(currentUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Firebase auth
        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(mBottomAppBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // assign fragments
        mInventoryFragment = new InventoryFragment();
        mPortalListFragment = new PortalListFragment();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            MenuItem item = mNavigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mBottomAppBar, R.string.drawer_open, R.string.drawer_close);
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
}
