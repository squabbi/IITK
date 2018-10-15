package com.squabbi.iitk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.model.InventoryItem;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Public adapter class for Recycling InventoryItem views and objects. Designed for use within
 * managing the inventory. It will display a given list of InventoryItems.
 */
public class InventoryCheckoutAdapter extends RecyclerView.Adapter<InventoryCheckoutAdapter.ViewHolder> {

    private List<InventoryItem> mInventoryItemList = new LinkedList<>();
    private OnModItemClickListener mListener;

    /** Public interface for listening for Item clicks. */
    public interface OnModItemClickListener {

        /**
         * Called when an item is clicked on within the view.
         * @param item Selected {@link InventoryItem} item.
         * @param position Selected item's position in the array/recycler.
         */
        void onModClicked(InventoryItem item, int position);
    }

    /**
     * Public constructor for the adapter.
     * @param listener OnModItemClickListener to listen to callback events.
     */
    public InventoryCheckoutAdapter(OnModItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Public method for setting the inventory item list and notifes the adapter of
     * the change.
     * @param itemList List of {@link InventoryItem} items.
     */
    public void setInventoryItemList(List<InventoryItem> itemList) {

        this.mInventoryItemList = itemList;
        notifyDataSetChanged();
    }

    /**
     * Inner-class for initalising the ViewHolder for InventoryItems.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventoryitem_name_textview)
        TextView mNameTv;

        @BindView(R.id.item_inventoryitem_description_textview)
        TextView mDescriptionTv;

        @BindView(R.id.item_inventoryitem_imageview)
        ImageView mImageView;

        /**
         * Public constructor for ViewHolder, takes in an itemView.
         * @param itemView View for each item.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // Set ItemOnClickListener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // Ensure the position selected exists and there is a listener registered
                    if (position != RecyclerView.NO_POSITION && mListener != null) {
                        // Get current item at position
                        InventoryItem item = mInventoryItemList.get(position);
                        mListener.onModClicked(item, position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public InventoryCheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventory_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryCheckoutAdapter.ViewHolder holder, int position) {
        InventoryItem inventoryItem = mInventoryItemList.get(position);
        Context context = holder.itemView.getContext();

        // Set text and images of Mods
        // Different if the item has a level
        if (inventoryItem.getLevel() != 0) {
            holder.mNameTv.setText(context.getString(inventoryItem.getNameResource(), inventoryItem.getLevel()));
        } else {
            holder.mNameTv.setText(inventoryItem.getNameResource());
        }

        holder.mDescriptionTv.setText(inventoryItem.getRarity().toString());
        holder.mImageView.setImageResource(inventoryItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        return mInventoryItemList.size();
    }
}
