package com.squabbi.iitk._interface;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnFirestoreItemClickListener {

    void onFirestoreItemClick(DocumentSnapshot documentSnapshot, int position);
}
