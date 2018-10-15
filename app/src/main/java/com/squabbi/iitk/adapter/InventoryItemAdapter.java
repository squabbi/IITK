package com.squabbi.iitk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.model.InventoryItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter class for Inventory Items, this class uses a pre-defined set of {@link InventoryItem}
 * to be populated by the ViewHolder.
 */
public class InventoryItemAdapter extends RecyclerView.Adapter<InventoryItemAdapter.ViewHolder> {

    private List<InventoryItem> mInventoryItemList;
    private OnModItemClickListener mListener;

    /** Public interface for listening to item's OnClicks */
    public interface OnModItemClickListener {
        /**
         * Method for getting the selected {@link InventoryItem} and it's position in the adapter.
         * @param item Selected {@link InventoryItem} item.
         * @param position Selected item's position in the array/adapter.
         */
        void onModClicked(InventoryItem item, int position);
    }

    /**
     * Public constructor for the inventory list. Sets the listener for item clicks in addition to the
     * pre-cofigured list of InventoryItems to display.
     * @param inventoryItemList List of {@link InventoryItem} items to display in the adapter.
     * @param listener The parent's OnModItemClickListener to listen and react to item OnClicks.
     */
    public InventoryItemAdapter(List<InventoryItem> inventoryItemList, OnModItemClickListener listener) {

        this.mInventoryItemList = inventoryItemList;
        this.mListener = listener;
    }

    /** Inner-class for configuring the ViewHolder to be used with the RecyclerView. */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventoryitem_name_textview)
        TextView mNameTv;

        @BindView(R.id.item_inventoryitem_description_textview)
        TextView mDescriptionTv;

        @BindView(R.id.item_inventoryitem_imageview)
        ImageView mImageView;

        /**
         * Constructor for ViewHolder which binds views to fields and sets the
         * onClick listener for the view.
         * @param itemView Inflated item view.
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
    public InventoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventory_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryItemAdapter.ViewHolder holder, int position) {
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
