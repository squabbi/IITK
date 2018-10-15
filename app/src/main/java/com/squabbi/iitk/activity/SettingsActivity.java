package com.squabbi.iitk.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.squabbi.iitk.R;

/**
 * Basic activity which contains the settings of the app. It allows the user to change some default
 * preferences and to log out of IITK.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }
}
