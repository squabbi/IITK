package com.squabbi.iitk.activity.ui.portalview;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.R;
import com.squabbi.iitk.databinding.FragmentPortalViewBinding;

public class PortalViewFragment extends Fragment {

    private PortalViewViewModel mViewModel;

    @BindView(R.id.portalview_mapview)
    MapView mMapView;

    public static PortalViewFragment newInstance() {
        return new PortalViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentPortalViewBinding binding = FragmentPortalViewBinding.inflate(inflater, container, false);
        ButterKnife.bind(this, binding.getRoot());

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(PortalViewViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
