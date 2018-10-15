package com.squabbi.iitk.activity.ui.portal.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.activity.ui.mainlistview.PortalListFragment;
import com.squabbi.iitk.util.Constants;
import com.squabbi.iitk.util.ViewModelFactory;

import static com.squabbi.iitk.util.Constants.PORTAL_REFERENCE_KEY;
import static com.squabbi.iitk.util.Constants.URL_CUSTOM_INGRESS;
import static com.squabbi.iitk.util.Constants.URL_DEFAULT_INGRESS;

/**
 * Activity that displays the passed in Portal's details, provides a map view and edit option
 * for the portal.
 */

public class PortalViewActivity extends AppCompatActivity implements PortalViewFragment.OnEditSelected,
        OnFragmentViewInteractionListener {

    private PortalViewViewModel mViewModel;
    private PortalViewFragment mViewFragment;
    private PortalViewEditFragment mEditFragment;

    private ActionBar mActionBar;

    @BindView(R.id.view_portal_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_view);

        // Instantiate fragments
        mViewFragment = PortalViewFragment.newInstance();
        mEditFragment = PortalViewEditFragment.newInstance();

        // Assign onEditSelected for ViewFragment
        mViewFragment.setOnEditSelected(this);

        // Set Toolbar as ActionBar
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_outline_close_24px);

        // Get string from intent
        mViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(getApplication(), getIntent().getStringExtra(PORTAL_REFERENCE_KEY)))
                .get(PortalViewViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mViewFragment)
                    .commitNow();
            invalidateOptionsMenu();
        }
    }

    @Override
    public void onEditSelected() {

        // Change fragment to the Edit fragment
        updateFragment(mEditFragment);
    }

    private void updateFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
        transitionSet.setDuration(700);
        transitionSet.setStartDelay(200);

        fragment.setSharedElementEnterTransition(transitionSet);

        Fragment previousFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        // Old fragment exit fade
        Fade exitFade = new Fade();
        exitFade.setDuration(200);
        previousFragment.setExitTransition(exitFade);

        // Enter fade transition
        Fade enterFade = new Fade();
        enterFade.setStartDelay(900);
        enterFade.setDuration(200);
        fragment.setEnterTransition(enterFade);

        View viewFab = findViewById(R.id.portalview_fab);
        fragmentTransaction.addSharedElement(viewFab, viewFab.getTransitionName());
        fragmentTransaction.replace(R.id.container, fragment, Constants.TAG_FRAGMENT_PORTAL_EDIT);
        fragmentTransaction.addToBackStack(Constants.TAG_FRAGMENT_PORTAL_EDIT);

        fragmentTransaction.commitAllowingStateLoss();

        // Invalidate menu
        invalidateOptionsMenu();
    }

    // TODO: finalise portal edit NOW
    public void launchPlacePicker(View view) {

    }

    @Override
    public void onViewPressed(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.portalview_fab:
                // Open intel view with the Portal's details
                openIntelMap();
        }
    }

    @Override
    public void onMenuItemPressed(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case android.R.id.home:
                // Check current fragment, then decide action.
                if (findFragmentByTag(Constants.TAG_FRAGMENT_PORTAL_EDIT) == mEditFragment) {
                    Toast.makeText(this, "currently edit fragment", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_portal_edit:
                onEditSelected();
                break;
        }
    }

    private Fragment findFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    private void openIntelMap() {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(Color.BLACK);
        CustomTabsIntent customTabsIntent = builder.build();

        GeoPoint geoPoint = mViewModel.getPortalGeoPoint().getValue();

        if (geoPoint != null) {
            // Format the string
            customTabsIntent.launchUrl(this, Uri.parse(String.format(URL_CUSTOM_INGRESS, geoPoint.getLatitude(), geoPoint.getLongitude())));
        } else {
            customTabsIntent.launchUrl(this, Uri.parse(URL_DEFAULT_INGRESS));
        }
    }
}
