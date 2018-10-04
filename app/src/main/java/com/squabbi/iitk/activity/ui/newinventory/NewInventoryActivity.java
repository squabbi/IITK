package com.squabbi.iitk.activity.ui.newinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.GridView;

import com.squabbi.iitk.R;

public class NewInventoryActivity extends AppCompatActivity {

    @BindView(R.id.lockers_gridview) GridView mLockerGridView;

    private NewInventoryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventory);
        ButterKnife.bind(this);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this).get(NewInventoryViewModel.class);
    }
}
