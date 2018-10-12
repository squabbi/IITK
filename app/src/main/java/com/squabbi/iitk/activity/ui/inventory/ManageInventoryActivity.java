package com.squabbi.iitk.activity.ui.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewActivity;
import com.squabbi.iitk.databinding.ActivityManageInventoryBinding;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.Weapon;
import com.squabbi.iitk.util.ViewModelFactory;

import java.util.LinkedList;
import java.util.List;

public class ManageInventoryActivity extends AppCompatActivity implements OnInventoryFragmentInteractionListener {

    private ManageInventoryViewModel mViewModel;

    private static final int NUM_PAGES = 8;
    private PagerAdapter mPagerAdapter;

    @BindView(R.id.add_inventory_items_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.add_inventory_items_toolbar)
    Toolbar mToolbar;

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot, int position) {

    }

    @Override
    public void onViewPressed(View view) {

    }

//    // Array of Locker ImageViews
//    @BindViews({ R.id.new_inv_green_locker_imageview, R.id.new_inv_blue_locker_imageview,
//            R.id.new_inv_white_locker_imageview, R.id.new_inv_red_locker_imageview, R.id.new_inv_yellow_locker_imageview,
//            R.id.new_inv_anniversary_locker_imageview })
//    List<ImageView> mLockerImageViews;
//
//    // Set onClickListener for Locker ImageViews
//    @OnClick({ R.id.new_inv_green_locker_imageview, R.id.new_inv_blue_locker_imageview,
//            R.id.new_inv_white_locker_imageview, R.id.new_inv_red_locker_imageview, R.id.new_inv_yellow_locker_imageview,
//            R.id.new_inv_anniversary_locker_imageview })
//    public void selectLocker(ImageView imageView) {
//
//        switch (imageView.getId()) {
//            case R.id.new_inv_green_locker_imageview:
//                mViewModel.setSelectedCapsule(0, !mViewModel.getSelectedCapsule(0));
//                break;
//            case R.id.new_inv_blue_locker_imageview:
//                mViewModel.setSelectedCapsule(1, !mViewModel.getSelectedCapsule(1));
//                break;
//            case R.id.new_inv_white_locker_imageview:
//                mViewModel.setSelectedCapsule(2, !mViewModel.getSelectedCapsule(2));
//                break;
//            case R.id.new_inv_red_locker_imageview:
//                mViewModel.setSelectedCapsule(3, !mViewModel.getSelectedCapsule(3));
//                break;
//            case R.id.new_inv_yellow_locker_imageview:
//                mViewModel.setSelectedCapsule(4, !mViewModel.getSelectedCapsule(4));
//                break;
//            case R.id.new_inv_anniversary_locker_imageview:
//                mViewModel.setSelectedCapsule(5, !mViewModel.getSelectedCapsule(5));
//                break;
//        }
//    }

    private class InventoryItemsPagerAdapter extends FragmentStatePagerAdapter {

        public InventoryItemsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AddCapsulesFragment.newInstance();
                case 1:
                    return AddModsFragment.newInstance();
                case 2:
                    return AddWeaponsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
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

        // Instantiate ViewPager and PagerAdapter
        mPagerAdapter = new InventoryItemsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

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
