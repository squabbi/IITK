package com.squabbi.iitk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Item;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryItemListAdapter extends FirestoreRecyclerAdapter<Item,
        InventoryItemListAdapter.ItemHolder> {

    private OnFirestoreItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public InventoryItemListAdapter(@NonNull FirestoreRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemHolder itemHolder, int i, @NonNull Item item) {

        itemHolder.mNameTv.setText(item.getClass().getName());
        itemHolder.mDescriptionTv.setText(item.getRarity().toString());
        itemHolder.mImageView.setImageResource(R.drawable.key_locker_anniversary);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory_item,
                parent, false);
        return new ItemHolder(itemView);
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventoryitem_imageview)
        ImageView mImageView;

        @BindView(R.id.item_inventoryitem_name_textview)
        TextView mNameTv;

        @BindView(R.id.item_inventoryitem_description_textview)
        TextView mDescriptionTv;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

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
}
