package com.squabbi.iitk.activity;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.dmgdesignuk.locationutils.easylocationutility.EasyLocationUtility;
import com.dmgdesignuk.locationutils.easylocationutility.LocationRequestCallback;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.mainlistview.InventoryListFragment;
import com.squabbi.iitk.activity.ui.mainlistview.PortalListFragment;
import com.squabbi.iitk.activity.ui.mainlistview.MainActivityViewModel;
import com.squabbi.iitk.activity.ui.portal.NewPortalActivity;
import com.squabbi.iitk.service.MyHoverMenuService;
import com.squabbi.iitk.util.Constants;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.mattcarroll.hover.overlay.OverlayPermission;

/**
 * This sets the content of the main activity with a app bar, navigation drawer,
 * and sets up the respective listeners and onOptionsItemSelected to handle the
 * changes from the user interacting with the navigation drawer.
 *
 * This is where the fragments are changed programmatically.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Butter Knife binding for the navigation drawer and toolbar
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.main_activity_appbar)
    BottomAppBar mBottomAppBar;

    // Fields for Dialog
    EditText mDialogInventoryNameEt;
    EditText mDialogInventoryDescriptionEt;
    TextInputLayout mInventoryNameInputLayout;
    TextInputLayout mInventoryDescriptionInputLayout;

    private MainActivityViewModel mViewModel;

    // Fragments
    private PortalListFragment mPortalListFragment;
    private InventoryListFragment mInventoryFragment;

    private static final Integer REQUEST_CODE_HOVER_PERMISSION = 91;
    private boolean mPermissionsRequested = false;

    // Constants

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE_HOVER_PERMISSION == requestCode) {
            mPermissionsRequested = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup and reference ViewModel
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setSupportActionBar(mBottomAppBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        // Assign Fragments.
        mPortalListFragment = new PortalListFragment();
        mInventoryFragment = new InventoryListFragment();

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
            case R.id.nav_portals:
                startActivity(new Intent(this, NewPortalActivity.class));
                break;
            case R.id.nav_inventory:
                // Show New Inventory dialog.
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_new_inventory, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_newinv_title)
                        .setView(dialogView)
                        .setPositiveButton(R.string.confirm, null)
                        .setNegativeButton(R.string.cancel, null)
                        .create();

                // Assign ViewIDs to fields.
                mDialogInventoryNameEt = dialogView.findViewById(R.id.dialog_newinv_name_edittext);
                mDialogInventoryDescriptionEt = dialogView.findViewById(R.id.dialog_newinv_description_edittext);

                mInventoryNameInputLayout = dialogView.findViewById(R.id.dialog_newinv_name_inputlayout);
                mInventoryDescriptionInputLayout = dialogView.findViewById(R.id.dialog_newinv_description_inputlayout);

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

                        Button positiveBtn = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                // Implement onClick confirm result.
                                // TODO: Implement colour picker here
                                if (validateText()) {
                                    mViewModel.addInventory(mDialogInventoryNameEt.getText().toString(),
                                            mDialogInventoryDescriptionEt.getText().toString(), -3992);
                                    alertDialog.dismiss();
                                }
                            }
                        });
                    }
                });

                alertDialog.show();
                break;
        }
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
                getCurrentLocation(Constants.LOCATION_GET_INTEL);
                break;
            case R.id.menu_open_hover:
                startHoverService();
                break;
            case android.R.id.home:
                // Open navigation bar
                toggleNavigationDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleNavigationDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void getCurrentLocation(final int type) {

        EasyLocationUtility locationUtility = new EasyLocationUtility(this);

        // Check if permission to access device location has been granted and ask for it if not
        if (locationUtility.permissionIsGranted()){
            // Permission is granted
            Toast.makeText(getApplication(), "Determining location...", Toast.LENGTH_LONG).show();

            locationUtility.getCurrentLocationOneTime(new LocationRequestCallback() {
                @Override
                public void onLocationResult(Location location) {
                    switch (type) {
                        // Regular Intel map
                        case Constants.LOCATION_GET_INTEL:
                            showIntelMap(location);
                            break;
                    }
                }

                @Override
                public void onFailedRequest(String s) {
                    Toast.makeText(getApplication(), "Failed to determine location.", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            // Permission not granted, ask for it
            locationUtility.requestPermission(EasyLocationUtility.RequestCodes.CURRENT_LOCATION_ONE_TIME);
            Toast.makeText(getApplication(), "Please run the action again.", Toast.LENGTH_LONG).show();
        }
    }

    private void showIntelMap(Location location) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(Color.BLACK);
        CustomTabsIntent customTabsIntent = builder.build();

        if (location != null) {
            // Format the string
            String locationString = "https://www.ingress.com/intel?ll=" + location.getLatitude() + "," + location.getLongitude() + "&z=15";
            customTabsIntent.launchUrl(this, Uri.parse(locationString));
        } else {
            customTabsIntent.launchUrl(this, Uri.parse("https://www.ingress.com/intel"));
        }
    }

    /**
     * Starts HoverService with Location passed to it to initalise the View.
     *
     * On Android M and above we need to ask the user for permission to display the Hover
     * menu within the "alert window" layer.  Use OverlayPermission to check for the permission
     * and to request it.
     */
    public void startHoverService() {

        if (!mPermissionsRequested && !OverlayPermission.hasRuntimePermissionToDrawOverlay(this)) {
            Intent intent = OverlayPermission.createIntentToRequestOverlayPermission(this);
            startActivityForResult(intent, REQUEST_CODE_HOVER_PERMISSION);
        } else { MyHoverMenuService.showFloatingMenu(this); }
    }


    private void updateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean validateText() {
        boolean passingStatus = true;

        // Check for empty name.
        if (isEmpty(mDialogInventoryNameEt)) {
            mInventoryNameInputLayout.setError(getString(R.string.inputlayout_error_emptyname));
            mInventoryNameInputLayout.setErrorEnabled(true);
            passingStatus = false;
        }

        // Check for empty description.
        if (isEmpty(mDialogInventoryDescriptionEt)) {
            mInventoryDescriptionInputLayout.setError(getString(R.string.inputlayout_error_emptydescription));
            mInventoryDescriptionInputLayout.setErrorEnabled(true);
            passingStatus = false;
        }

        return passingStatus;
    }
}
