package com.squabbi.iitk._interface;

import com.squabbi.iitk.model.InventoryItem;

/**
 * A public interface for listening to regular RecyclerView OnItemClick interactions. This is used
 * for {@link com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity}.
 */
public interface OnInventoryItemClickListener {
    /**
     * Provides the listener with the InventoryItem item and it's position in the Recycler adapter.
     * @param item selected InventoryItem.
     * @param position current position of the item in the RecyclerView/
     */
    void onItemSelected(InventoryItem item, int position);
}
