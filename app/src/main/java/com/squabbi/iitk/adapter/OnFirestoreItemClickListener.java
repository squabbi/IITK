package com.squabbi.iitk.adapter;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnInventoryItemClickListener {

    void onItemClick(DocumentSnapshot documentSnapshot, int position);
}
