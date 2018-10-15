package com.squabbi.iitk._interface;

import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.Nullable;

/**
 * A public interface for Firestore Items, to be used with FirestoreRecyclerViews.
 */
public interface OnFirestoreItemClickListener {

    /**
     * Returns the documentSnapshot object, and it's position in the RecyclerView.
     * @param documentSnapshot Firebase document snapshot of the selected item.
     * @param type Optional type integer, can be used for position or determining item type.
     */
    void onFirestoreItemClick(DocumentSnapshot documentSnapshot, @Nullable Integer type);
}
