package com.squabbi.iitk.adapter;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnFirestoreItemClickListener {

    void onFirestoreItemClick(DocumentSnapshot documentSnapshot, int position);
}
