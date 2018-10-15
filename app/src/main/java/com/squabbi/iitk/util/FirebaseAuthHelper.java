package com.squabbi.iitk.util;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthHelper {

    private static FirebaseAuthHelper mAuthHelper;
    private FirebaseAuth mAuth;

    public static FirebaseAuthHelper getInstance() {
        if (mAuthHelper == null) {
            mAuthHelper = new FirebaseAuthHelper();
        }
        return mAuthHelper;
    }

    private FirebaseAuthHelper() {
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }
}
