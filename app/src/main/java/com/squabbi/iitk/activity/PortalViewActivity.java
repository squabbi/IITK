package com.squabbi.iitk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.mainlistview.PortalListFragment;
import com.squabbi.iitk.activity.ui.portalview.PortalViewFragment;
import com.squabbi.iitk.activity.ui.portalview.PortalViewViewModel;
import com.squabbi.iitk.util.ViewModelFactory;

public class PortalViewActivity extends AppCompatActivity {

    PortalViewViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_view);

        // Get string from intent
        mViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(getIntent().getStringExtra(PortalListFragment.PORTAL_ID_KEY)))
                .get(PortalViewViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PortalViewFragment.newInstance())
                    .commitNow();
        }
    }
}
