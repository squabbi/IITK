package com.squabbi.iitk.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Portal;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PortalAdapter extends PortalFirestoreAdapter<PortalAdapter.ViewHolder> {

    public interface OnPortalSelectedListener {

        void onPortalSelected(DocumentSnapshot portal);

    }

    private OnPortalSelectedListener mListener;

    public PortalAdapter(Query query, OnPortalSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @NonNull
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_portal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_portal_colour)
        View colourBarView;

        @BindView(R.id.item_portal_name)
        TextView nameTv;

        @BindView(R.id.item_portal_location)
        TextView locationTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot, final OnPortalSelectedListener listener) {

            Portal portal = snapshot.toObject(Portal.class);

            colourBarView.setBackgroundColor(Color.CYAN);
            nameTv.setText(portal.getName());
            locationTv.setText(portal.getGeoPoint().toString());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPortalSelected(snapshot);
                    }
                }
            });
        }
    }
}
