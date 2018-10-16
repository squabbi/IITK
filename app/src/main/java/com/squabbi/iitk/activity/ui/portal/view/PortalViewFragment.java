package com.squabbi.iitk.activity.ui.portal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.databinding.FragmentPortalViewBinding;

/**
 * Simple fragment which displays the details of the current portal.
 * Use the factory method {@link PortalViewFragment#newInstance()} to create a new
 * instance of this fragment.
 *
 * The parent Activity must implement an {@link OnFragmentViewInteractionListener}.
 */

public class PortalViewFragment extends Fragment {

    private PortalViewViewModel mViewModel;
    private OnEditSelected mOnEditSelected;
    private OnFragmentViewInteractionListener mListener;

    @BindView(R.id.portalview_mapview)
    MapView mMapView;

    @BindView(R.id.portalview_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.portalview_collapsingtoolbar)
    CollapsingToolbarLayout mToolbarLayout;

    @BindView(R.id.portalview_appbar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.portalview_fab)
    FloatingActionButton mActionButton;

    public static PortalViewFragment newInstance() {
        return new PortalViewFragment();
    }

    public interface OnEditSelected {
        void onEditSelected();
    }

    public void setOnEditSelected(OnEditSelected onEditSelected) {
        this.mOnEditSelected = onEditSelected;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_portalview, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentPortalViewBinding binding = FragmentPortalViewBinding.inflate(inflater, container, false);
        ButterKnife.bind(this, binding.getRoot());

        // Set up action bar within the fragment.
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24px);
        setHasOptionsMenu(true); // Enables the overflow menu.

        mToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

        mMapView.onCreate(savedInstanceState);

        // Set observers
        mViewModel.getPortalGeoPoint().observe(this, new Observer<GeoPoint>() {
            @Override
            public void onChanged(final GeoPoint geoPoint) {

                // Check in case Portal does not have a location attached
                if (geoPoint != null) {
                    final LatLng location = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());

                    // Set MapView as visible
                    mMapView.setVisibility(View.VISIBLE);

                    // Save the map marker
                    mMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            googleMap.addMarker(new MarkerOptions().position(location).title(mViewModel.getPortalName().getValue()));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                            mMapView.onResume();
                        }
                    });
                } else {
                    // When geoPoint is null, make the map invisible
                    mMapView.setVisibility(View.GONE);
                }
            }
        });

        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Close the activity
                getActivity().finish();
                break;

            case R.id.menu_portal_delete:
                String name = mViewModel.getPortalName().getValue();
                mViewModel.deletePortal(mViewModel.getPortalDocumentPath());
                // Close activity
                getActivity().finish();
                // Show toast
                Toast.makeText(getContext(), getString(R.string.detail_portal_delete_toast, name), Toast.LENGTH_SHORT)
                    .show();
                break;
        }

        return true;
    }

    @OnClick(R.id.portalview_fab)
    void fabOnClick(View view) {
        // Open intel map with coordinates of Portal
        mListener.onViewPressed(view);
    }

    private void performTransition()
    {
        // Create new instance of edit fragment
        PortalViewEditFragment editFragment = PortalViewEditFragment.newInstance();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        transitionSet.setDuration(1000);
        transitionSet.setStartDelay(300);
        editFragment.setSharedElementEnterTransition(transitionSet);

        fragmentTransaction.addSharedElement(mActionButton, getString(R.string.trans_fab));
        fragmentTransaction.replace(R.id.container, editFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentViewInteractionListener) {
            mListener = (OnFragmentViewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentViewInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(PortalViewViewModel.class);
    }
}
