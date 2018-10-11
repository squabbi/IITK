package com.squabbi.iitk.adapter;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnFirestoreItemClickListener {

    void onItemClick(DocumentSnapshot documentSnapshot, int position);
}
