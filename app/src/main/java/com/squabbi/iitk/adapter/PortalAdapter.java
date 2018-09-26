package com.squabbi.iitk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Portal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PortalAdapter extends FirestoreRecyclerAdapter<Portal, PortalAdapter.PortalHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PortalAdapter(@NonNull FirestoreRecyclerOptions<Portal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PortalHolder portalHolder, int i, @NonNull Portal portal) {
        portalHolder.mNameTv.setText(portal.getName());

        // Check location details
        GeoPoint geoPoint = portal.getGeoPoint();
        String friendlyLocation = portal.getFriendlyLocation();

        if (geoPoint != null) {
            // Set LatLng to location TextView
            portalHolder.mLocationTv.setText(portal.getGeoPoint().toString());
        } else if (friendlyLocation != null && !friendlyLocation.isEmpty()) {
            // Always try to default to friendly location
            portalHolder.mLocationTv.setText(portal.getFriendlyLocation());
        } else {
            // Both geoPoint and friendlyLocation is null or empty
            portalHolder.mLocationTv.setText(R.string.portal_item_no_location);
        }

        // Set background colour from Portal
        portalHolder.mCardView.setCardBackgroundColor(portal.getColour());
    }

    @NonNull
    @Override
    public PortalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_portal,
                parent, false);
        return new PortalHolder(itemView);
    }

    static class PortalHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_portal_name)
        TextView mNameTv;

        @BindView(R.id.item_portal_location)
        TextView mLocationTv;

        @BindView(R.id.item_portal_card_layout)
        CardView mCardView;

        public PortalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static List<DocumentSnapshot> mSnapshots = new ArrayList<>();
    private OnPortalSelectedListener mListener;

    public interface OnPortalSelectedListener {
        void onPortalSelected(DocumentSnapshot portal);
    }

}
