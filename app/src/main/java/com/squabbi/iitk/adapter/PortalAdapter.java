package com.squabbi.iitk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Portal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {

    private static List<DocumentSnapshot> mSnapshots = new ArrayList<>();
    private OnPortalSelectedListener mListener;

    public interface OnPortalSelectedListener {
        void onPortalSelected(DocumentSnapshot portal);
    }

    public static void clearSnapshots() {
        mSnapshots.clear();
    }

    public PortalAdapter(OnPortalSelectedListener listener) {
        mListener = listener;
    }

    public void setDocChangeData(List<DocumentChange> documentChanges) {
        // Dispatch the event
        for (DocumentChange change : documentChanges) {
            switch (change.getType()) {
                case ADDED:
                    onDocumentAdded(change);
                    break;
                case MODIFIED:
                    onDocumentModified(change);
                    break;
                case REMOVED:
                    onDocumentRemoved(change);
                    break;
            }
        }
        notifyDataSetChanged();
    }

    protected void onDocumentAdded(DocumentChange change) {
        mSnapshots.add(change.getNewIndex(), change.getDocument());
        notifyItemInserted(change.getNewIndex());
    }

    protected void onDocumentModified(DocumentChange change) {
        if (change.getOldIndex() == change.getNewIndex()) {
            // Item changed but remained in the same position
            mSnapshots.set(change.getOldIndex(), change.getDocument());
            notifyItemChanged(change.getOldIndex());
        } else {
            // Item changed and changed position
            mSnapshots.remove(change.getOldIndex());
            mSnapshots.add(change.getNewIndex(), change.getDocument());
            notifyItemMoved(change.getOldIndex(), change.getNewIndex());
        }
    }

    protected void onDocumentRemoved(DocumentChange change) {
        mSnapshots.remove(change.getOldIndex());
        notifyItemRemoved(change.getOldIndex());
    }

    @NonNull
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_portal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder holder, int position) {
        holder.bind(mSnapshots.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mSnapshots.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_portal_name)
        TextView nameTv;

        @BindView(R.id.item_portal_location)
        TextView locationTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // Change this when you need to test different livedata
        public void bind(final DocumentSnapshot portal, final OnPortalSelectedListener listener) {
            // Create portal object
            Portal _portal = portal.toObject(Portal.class);

            nameTv.setText(_portal.getName());
            locationTv.setText(_portal.getGeoPoint().toString());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPortalSelected(portal);
                    }
                }
            });
        }
    }
}
