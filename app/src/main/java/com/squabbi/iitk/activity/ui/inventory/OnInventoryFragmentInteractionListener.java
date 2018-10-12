package com.squabbi.iitk.activity.ui.inventory;

import android.view.View;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnInventoryFragmentInteractionListener {

    void onItemSelected(DocumentSnapshot documentSnapshot, int position);
    void onViewPressed(View view);
}
