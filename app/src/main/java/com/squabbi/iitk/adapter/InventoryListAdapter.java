package com.squabbi.iitk.adapter;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Inventory;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryListAdapter extends FirestoreRecyclerAdapter<Inventory,
        InventoryListAdapter.InventoryHolder> {

    private OnInventoryItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public InventoryListAdapter(@NonNull FirestoreRecyclerOptions<Inventory> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InventoryListAdapter.InventoryHolder inventoryHolder, int i, @NonNull Inventory inventory) {

        inventoryHolder.mNameTv.setText(inventory.getName());
        inventoryHolder.mDescriptionTv.setText(inventory.getDescription());

        if (inventory.getColour() != null) {
            inventoryHolder.mImageView.setColorFilter(inventory.getColour(),
                    PorterDuff.Mode.OVERLAY);
        }
    }

    @NonNull
    @Override
    public InventoryListAdapter.InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory,
                parent, false);
        return new InventoryHolder(itemView);
    }

    class InventoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventory_name)
        TextView mNameTv;

        @BindView(R.id.item_inventory_description)
        TextView mDescriptionTv;

        @BindView(R.id.item_inventory_imageview)
        ImageView mImageView;

        public InventoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // OnClickListener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // Ensure the position selected exists and there is a listener registered
                    if (position != RecyclerView.NO_POSITION && mListener != null) {
                        mListener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnInventoryItemClickListener {

        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnInventoryItemClickListener(OnInventoryItemClickListener listener) {

        this.mListener = listener;
    }
}
