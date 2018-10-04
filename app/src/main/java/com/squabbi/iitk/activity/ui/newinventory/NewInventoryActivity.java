package com.squabbi.iitk.activity.ui.newinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.util.Constants;

import java.util.List;

public class NewInventoryActivity extends AppCompatActivity {

    private NewInventoryViewModel mViewModel;

    // Array of Locker ImageViews
    @BindViews({ R.id.new_inv_green_locker_imageview, R.id.new_inv_blue_locker_imageview,
            R.id.new_inv_white_locker_imageview, R.id.new_inv_red_locker_imageview, R.id.new_inv_yellow_locker_imageview,
            R.id.new_inv_anniversary_locker_imageview })
    List<ImageView> mLockerImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventory);
        ButterKnife.bind(this);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this).get(NewInventoryViewModel.class);

        // Observe livedata for checkboxes
        mViewModel.getSelectedCapsules().observe(this, new Observer<boolean[]>() {
            @Override
            public void onChanged(boolean[] booleans) {
                // Set the checked status depending on array
                for (int i = 0; i < Constants.LockerTypes.length; i++) {
                    if (booleans[i]) {
                        mLockerImageViews.get(i).setBackground(new ColorDrawable(Color.RED));
                    }
                }
            }
        });

        mViewModel.setSelectedCapsule(2, true);
    }
}
