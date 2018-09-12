package com.squabbi.iitk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.squabbi.iitk.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitWelcomeActivity extends AppCompatActivity {

    public static final String AGREEMENT_KEY = "agreement";

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
    }

    @OnClick(R.id.welcome_skip_btn)
    public void skipLogin() {
        // Save preference and show main activity
        FastSave.getInstance().saveBoolean(AGREEMENT_KEY, true);
        // Launch the main activity
        startMainActivityAndFinish();
    }

    @OnClick(R.id.welcome_sign_in_btn)
    public void signIn() {
        // Launch LoginActivity
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void startMainActivityAndFinish() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
