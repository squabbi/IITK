package com.squabbi.iitk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.GridView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.KeyLockerAdapter;
import com.squabbi.iitk.viewmodel.NewInventoryViewModel;

public class NewInventoryActivity extends AppCompatActivity {

    @BindView(R.id.lockers_gridview) GridView mLockerGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventory);
        ButterKnife.bind(this);

        mLockerGridView.setAdapter(new KeyLockerAdapter(this));
    }
}
