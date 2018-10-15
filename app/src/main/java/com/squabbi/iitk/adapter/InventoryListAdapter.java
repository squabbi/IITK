package com.squabbi.iitk.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.model.Inventory;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Inventory List adapter which extends FirebaseUI's FirestoreRecyclerAdapter. This converts
 * and abstracts the necessary functionality for showing Firestore Queries as objects into a UI element.
 */

public class InventoryListAdapter extends FirestoreRecyclerAdapter<Inventory,
        InventoryListAdapter.InventoryHolder> {

    private OnFirestoreItemClickListener mListener;

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

        // Set background colour from Portal if it's not null.
        if (inventory.getColour() != null) inventoryHolder.mCardView.setCardBackgroundColor(inventory.getColour());
        else inventoryHolder.mCardView.setCardBackgroundColor(Color.GRAY);
    }

    @NonNull
    @Override
    public InventoryListAdapter.InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory,
                parent, false);
        return new InventoryHolder(itemView);
    }

    /** Inner-class to bind the item view */
    class InventoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventory_name)
        TextView mNameTv;

        @BindView(R.id.item_inventory_description)
        TextView mDescriptionTv;

        @BindView(R.id.item_inventory_card_layout)
        CardView mCardView;

        /**
         * Constructor to bind the view and implement OnClick listeners.
         * @param itemView View to be binded to.
         */
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
                        mListener.onFirestoreItemClick(getSnapshots().getSnapshot(position), 1);
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
