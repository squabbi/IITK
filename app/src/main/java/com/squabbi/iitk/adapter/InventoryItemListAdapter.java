package com.squabbi.iitk.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.model.FirestoreItem;
import com.squabbi.iitk.model.InventoryItem;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.PortalKey;
import com.squabbi.iitk.model.Weapon;
import com.squabbi.iitk.util.InventoryItemConverter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Inventory Item List adapter which extends FirebaseUI's FirestoreRecyclerAdapter. This converts
 * and abstracts the necessary functionality for showing Firestore Queries as objects into a UI element.
 */

public class InventoryItemListAdapter extends FirestoreRecyclerAdapter<FirestoreItem,
        InventoryItemListAdapter.ItemHolder> {

    private OnFirestoreItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options FirestoreRecycler Options, uses the Query passed through the builder.
     * @param context context for registering the required listeners
     */
    public InventoryItemListAdapter(@NonNull FirestoreRecyclerOptions<FirestoreItem> options, Context context) {
        super(options);

        if (context instanceof OnFirestoreItemClickListener) {
            mListener = (OnFirestoreItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFirestoreItemClickListener");
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemHolder itemHolder, int i, @NonNull FirestoreItem item) {

        // Enable access to resources
        Resources resources = itemHolder.mDescriptionTv.getResources();

        InventoryItem convertedItem = InventoryItemConverter.determineInventoryItem(item);
        // NAME
        itemHolder.mNameTv.setText(resources.getString(convertedItem.getNameResource(), convertedItem.getLevel()));
        // RARITY, for KEYS, display PORTAL NAME
        if (item.getItemType() == Item.ItemType.KEY) {
            // Display Portal location
        } else {
            itemHolder.mDescriptionTv.setText(convertedItem.getRarity().toString());
        }
        // PICTURE
        itemHolder.mImageView.setImageResource(convertedItem.getImageResource());
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory_item,
                parent, false);
        return new ItemHolder(itemView);
    }

    /** Inner-class to bind views to the ItemHolder view */
    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventoryitem_imageview)
        ImageView mImageView;

        @BindView(R.id.item_inventoryitem_name_textview)
        TextView mNameTv;

        @BindView(R.id.item_inventoryitem_description_textview)
        TextView mDescriptionTv;

        /**
         * Constructor for ItemHolder ViewHolder to bind the view using ButterKnife.
         * @param itemView View for each item to be binded to.
         */
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // Ensure the position selected exists and there is a listener registered
                    if (position != RecyclerView.NO_POSITION && mListener != null) {
                        mListener.onFirestoreItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
}
