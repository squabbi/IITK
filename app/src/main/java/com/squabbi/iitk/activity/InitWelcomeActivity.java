package com.squabbi.iitk.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squabbi.iitk.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitWelcomeActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private static final String TAG = "InitWelcomeActivity";
    private static final String AGREEMENT_KEY = "agreement";
    private static final Integer RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_welcome);

        // Check if the user has been past the welcome screen
        // i.e. agreed to the terms and conditions
        if (FastSave.getInstance().getBoolean(AGREEMENT_KEY)) {
            // Launch MainActivity
            startMainActivityAndFinish();
        }

        ButterKnife.bind(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Get Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.welcome_skip_btn)
    public void skipLogin() {
        // Save preference and show main activity
        FastSave.getInstance().saveBoolean(AGREEMENT_KEY, true);
        // Launch the main activity
        startMainActivityAndFinish();
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
            catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        Snackbar.make(findViewById(R.id.welcome_main_layout),
                R.string.google_signin_progress,
                Snackbar.LENGTH_LONG).show();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

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
