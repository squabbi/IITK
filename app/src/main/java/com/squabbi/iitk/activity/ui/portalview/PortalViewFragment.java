package com.squabbi.iitk.activity.ui.portalview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

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
import com.squabbi.iitk.databinding.FragmentPortalViewBinding;

public class PortalViewFragment extends Fragment {

    private PortalViewViewModel mViewModel;

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
        mViewModel.getPortaGeoPoint().observe(this, new Observer<GeoPoint>() {
            @Override
            public void onChanged(final GeoPoint geoPoint) {
                final LatLng location = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());

                // Save the map marker
                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.addMarker(new MarkerOptions().position(location).title(mViewModel.getPortalName().getValue()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                        mMapView.onResume();
                    }
                });
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

            case R.id.menu_portal_edit:
                // TODO: Open edit fragment
                break;

            case R.id.menu_portal_share:
                // TODO: Implement share
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(PortalViewViewModel.class);
    }
}
