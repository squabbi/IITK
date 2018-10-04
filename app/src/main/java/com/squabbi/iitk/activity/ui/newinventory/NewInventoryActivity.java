package com.squabbi.iitk.activity.ui.newinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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

    // Set onClickListener for Locker ImageViews
    @OnClick({ R.id.new_inv_green_locker_imageview, R.id.new_inv_blue_locker_imageview,
            R.id.new_inv_white_locker_imageview, R.id.new_inv_red_locker_imageview, R.id.new_inv_yellow_locker_imageview,
            R.id.new_inv_anniversary_locker_imageview })
    public void selectLocker(ImageView imageView) {

        switch (imageView.getId()) {
            case R.id.new_inv_green_locker_imageview:
                mViewModel.setSelectedCapsule(0, !mViewModel.getSelectedCapsule(0));
                break;
            case R.id.new_inv_blue_locker_imageview:
                mViewModel.setSelectedCapsule(1, !mViewModel.getSelectedCapsule(1));
                break;
            case R.id.new_inv_white_locker_imageview:
                mViewModel.setSelectedCapsule(2, !mViewModel.getSelectedCapsule(2));
                break;
            case R.id.new_inv_red_locker_imageview:
                mViewModel.setSelectedCapsule(3, !mViewModel.getSelectedCapsule(3));
                break;
            case R.id.new_inv_yellow_locker_imageview:
                mViewModel.setSelectedCapsule(4, !mViewModel.getSelectedCapsule(4));
                break;
            case R.id.new_inv_anniversary_locker_imageview:
                mViewModel.setSelectedCapsule(5, !mViewModel.getSelectedCapsule(5));
                break;
        }
    }

    private static void setUnselected(ImageView imageView) {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(matrix);

        imageView.setColorFilter(colorMatrixColorFilter);
        imageView.setImageAlpha(128);
    }

    private static void setSelected(ImageView imageView) {

        imageView.setColorFilter(null);
        imageView.setImageAlpha(255);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventory);
        ButterKnife.bind(this);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this).get(NewInventoryViewModel.class);

        // Set initial status of Lockers
        for (ImageView v : mLockerImageViews) {
            setUnselected(v);
        }

        // Observe LiveData for checkboxes
        mViewModel.getSelectedCapsules().observe(this, new Observer<boolean[]>() {
            @Override
            public void onChanged(boolean[] booleans) {
                // Set the checked status depending on array
                for (int i = 0; i < Constants.LockerTypes.length; i++) {
                    if (booleans[i]) {
                        setSelected(mLockerImageViews.get(i));
                    } else {
                        // Unhighlight the capsule
                        setUnselected(mLockerImageViews.get(i));
                    }
                }
            }
        });
    }
}
