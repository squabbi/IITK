package com.squabbi.iitk.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Mod;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModItemAdapter extends RecyclerView.Adapter<ModItemAdapter.ViewHolder> {

    private List<Mod> mModList;
    private OnModItemClickListener mListener;

    public interface OnModItemClickListener {

        void onModClicked(int position);
    }

    public ModItemAdapter(List<Mod> modList, OnModItemClickListener listener) {

        this.mModList = modList;
        this.mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_inventoryitem_name_textview)
        TextView mNameTv;

        @BindView(R.id.item_inventoryitem_description_textview)
        TextView mDescriptionTv;

        @BindView(R.id.item_inventoryitem_imageview)
        ImageView mImageView;

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
                        mListener.onModClicked(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ModItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventory_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModItemAdapter.ViewHolder holder, int position) {
        Mod mod = mModList.get(position);

        // Set text and images of Mods
        holder.mNameTv.setText(mod.getName());
        holder.mDescriptionTv.setText(mod.getRarity().toString());
        holder.mImageView.setImageResource(mod.getImageResource());
    }

    @Override
    public int getItemCount() {
        return mModList.size();
    }
}
