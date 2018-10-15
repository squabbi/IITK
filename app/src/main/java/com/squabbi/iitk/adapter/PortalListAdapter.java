package com.squabbi.iitk.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.firestore.GeoPoint;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.model.Portal;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Portal List adapter which extends FirebaseUI's FirestoreRecyclerAdapter. This converts
 * and abstracts the necessary functionality for showing Firestore Queries as objects into a UI element.
 */

public class PortalListAdapter extends FirestoreRecyclerAdapter<Portal, PortalListAdapter.PortalHolder> {

    private OnFirestoreItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PortalListAdapter(@NonNull FirestoreRecyclerOptions<Portal> options) {
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

        // Set background colour from Portal if it's not null.
        if (portal.getColour() != null) portalHolder.mCardView.setCardBackgroundColor(portal.getColour());
        else portalHolder.mCardView.setCardBackgroundColor(Color.GRAY);
    }

    @NonNull
    @Override
    public PortalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_portal,
                parent, false);
        return new PortalHolder(itemView);
    }

    /** Inner-class for binding the view with items */
    class PortalHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_portal_name)
        TextView mNameTv;

        @BindView(R.id.item_portal_location)
        TextView mLocationTv;

        @BindView(R.id.item_portal_card_layout)
        CardView mCardView;

        /**
         * Constructor for binding the view and setting OnClick.
         * @param itemView View to be binded.
         */
        public PortalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // OnClickListener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // Ensure selected position exists and there is a listener
                    if (position != RecyclerView.NO_POSITION && mListener != null) {
                        mListener.onFirestoreItemClick(getSnapshots().getSnapshot(position), 0);
                    }
                }
            });
        }
    }

    /**
     * Sets the OnClick listener which is called when an item is clicked.
     * @param listener OnFirestoreItemClickListener to listen to callbacks.
     */
    public void setOnItemClickListener(OnFirestoreItemClickListener listener) {
        this.mListener = listener;
    }
}
