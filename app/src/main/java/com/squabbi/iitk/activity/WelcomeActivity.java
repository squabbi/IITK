package com.squabbi.iitk.activity;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.appizona.yehiahd.fastsave.FastSave;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squabbi.iitk.R;
import com.squabbi.iitk.util.FirebaseAuthHelper;
import com.squabbi.iitk.util.FirebaseRepository;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Welcome activity is the first activity which is launched. This activity connects to
 * Firebase for Authentication.
 */
public class WelcomeActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuthHelper mAuthHelper = FirebaseAuthHelper.getInstance();
    private static final String TAG = "WelcomeActivity";
    private static final String AGREEMENT_KEY = "agreement";
    private static final Integer RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        // Check if the user is logged in and has agreed
        if (FastSave.getInstance().getBoolean(AGREEMENT_KEY) && mAuthHelper.getAuth().getCurrentUser() != null) {
            // Launch MainActivity
            startMainActivityAndFinish();
        }

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check for result returned from Google sign in activity
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google sign in successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }
            catch (ApiException apiEx) {
                // TODO: Catch int exceptions, i.e. 7 is no internet I think
                apiEx.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        Snackbar.make(findViewById(R.id.welcome_main_layout),
                R.string.google_signin_progress,
                Snackbar.LENGTH_LONG).show();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuthHelper.getAuth().signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Save agreement
                    FastSave.getInstance().saveBoolean(AGREEMENT_KEY, true);
                    // Sign in success, allow user to proceed
                    startMainActivityAndFinish();
                } else {
                    // Sign in failed
                    Snackbar.make(findViewById(R.id.welcome_main_layout),
                            R.string.google_signin_failed,
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * ButterKnife OnClick bind for the Google Sign In button.
     */
    @OnClick(R.id.welcome_sign_in_btn)
    public void signIn() {
        // Create and start Google sign-in intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void startMainActivityAndFinish() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
