package com.squabbi.iitk.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squabbi.iitk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyLockerAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mKeyLockerNames;
    private TypedArray mKeyLockerResourceIds;
    private final LayoutInflater mInflater;

    public KeyLockerAdapter(Context context) {
        this.mContext = context;
        mKeyLockerNames = mContext.getResources().getStringArray(R.array.lockers);
        mKeyLockerResourceIds = mContext.getResources().obtainTypedArray(R.array.key_locker_images);
        mInflater = LayoutInflater.from(mContext);
    }

    // Expose state of CheckBox



    @Override
    public int getCount() {
        return mKeyLockerNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.grid_locker, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        // Set the appropriate text and images for each key locker
        holder.mLockerTv.setText(mKeyLockerNames[position]);
        holder.mLockerImageView.setImageDrawable(mKeyLockerResourceIds.getDrawable(position));

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.locker_imageview) ImageView mLockerImageView;
        @BindView(R.id.locker_name_tv) TextView mLockerTv;
        @BindView(R.id.locker_selected_checkbox) CheckBox mLockerCb;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
