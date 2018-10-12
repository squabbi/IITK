package com.squabbi.iitk.activity.ui.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewActivity;
import com.squabbi.iitk.databinding.ActivityManageInventoryBinding;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.Weapon;
import com.squabbi.iitk.util.Constants;
import com.squabbi.iitk.util.ViewModelFactory;

import java.util.LinkedList;
import java.util.List;

public class ManageInventoryActivity extends AppCompatActivity {

    private ManageInventoryViewModel mViewModel;
    private Integer mColour;

    private static final String TAG_CHROMA_DIALOG = "TAG_CHROMA_DIALOG";

    @BindView(R.id.new_inventory_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.new_inventory_name_edittext)
    EditText mNameEt;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(getIntent().getStringExtra(InventoryViewActivity.INVENTORY_ID_KEY)))
                .get(ManageInventoryViewModel.class);

        // Bind the ViewModel to XML
        ActivityManageInventoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_inventory);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        ButterKnife.bind(this);

        // Set initial status of Lockers
        for (ImageView v : mLockerImageViews) {
            setUnselected(v);
        }

        // Observe LiveData for selcted lockers
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

        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar and
        // enable the close button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24px);

        // TODO: Set fab for colour picker
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                // Close the keyboard and submit strings to ViewModel.
                closeKeyboard();
                // Validate entries
                if (validateText()) {

                }

                // Add portal and exit activity
                addInventory();
                finish();

                break;
            case android.R.id.home:
                // Close activity
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager iMm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            iMm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean validateText() {
        boolean passingStatus = true;

        // Check for empty name
        if (isEmpty(mNameEt)) {
            //mNameInputLayout.setError(getString(R.string.inputlayout_error_emptyname));
            //mNameInputLayout.setErrorEnabled(true);
            passingStatus = false;
        }

        return passingStatus;
    }

    private void addInventory() {

        List<Item> sampleItems = new LinkedList<>();
        sampleItems.add(new Weapon(Weapon.WeaponType.BURSTER, Item.Rarity.RARE, 4));

        mViewModel.addItemsToInventory(sampleItems);
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

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
