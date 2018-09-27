package com.squabbi.iitk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.portalview.PortalViewFragment;

public class PortalViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portal_view_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PortalViewFragment.newInstance())
                    .commitNow();
        }
    }
}
