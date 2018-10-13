package com.squabbi.iitk.activity.ui.inventory.manage;

import android.view.View;

import com.squabbi.iitk.model.InventoryItem;

public interface OnInventoryFragmentInteractionListener {

    void onItemSelected(InventoryItem item, int position);
    void onViewPressed(View view);
}
