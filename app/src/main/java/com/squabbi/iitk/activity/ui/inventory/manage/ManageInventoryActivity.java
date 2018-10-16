package com.squabbi.iitk.activity.ui.inventory.manage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk._interface.OnInventoryItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddCapsulesKeysFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddCubesFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddModsFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddResonatorsFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddPowerupsFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.AddWeaponsFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.InventoryCheckoutFragment;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewActivity;
import com.squabbi.iitk.databinding.ActivityManageInventoryBinding;
import com.squabbi.iitk.model.InventoryItem;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.Weapon;
import com.squabbi.iitk.util.ViewModelFactory;

import java.util.LinkedList;
import java.util.List;

import static com.squabbi.iitk.util.Constants.INVENTORY_ID_KEY;

/**
 * An activity which allows the user to add items to be added to their selected inventory.
 * This activity implements both an OnFragmentViewInteractionListener, as well as an
 * OnIventoryItemClickListener, to handle those respective events from both adapters and views of
 * fragments.
 */

public class ManageInventoryActivity extends AppCompatActivity implements OnFragmentViewInteractionListener, OnInventoryItemClickListener {

    @Override
    public void onViewPressed(View view) {
        // Use switch to handle different button presses (including menus)
    }

    @Override
    public void onMenuItemPressed(MenuItem menuItem) {

    }

    @Override
    public void onItemSelected(InventoryItem item, int position) {
        // Add item to viewmode's thing
        mViewModel.addItemToInventoryCart(item);
    }

    private ManageInventoryViewModel mViewModel;

    private static final int NUM_PAGES = 6;
    private String[] mPageNames;
    private PagerAdapter mPagerAdapter;

    @BindView(R.id.add_inventory_items_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.add_inventory_items_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.add_inventory_items_tablayout)
    TabLayout mTabLayout;

    /**
     * Private inner class which creates a PageAdapter for the Inventory Items list.
     */
    private class InventoryItemsPagerAdapter extends FragmentStatePagerAdapter {

        /**
         * Public constructor for the PagerAdapter.
         * @param fragmentManager Requires the FragmentManager of the activity.
         */
        public InventoryItemsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AddPowerupsFragment.newInstance();
                case 1:
                    return AddCapsulesKeysFragment.newInstance();
                case 2:
                    return AddModsFragment.newInstance();
                case 3:
                    return AddCubesFragment.newInstance();
                case 4:
                    return AddResonatorsFragment.newInstance();
                case 5:
                    return AddWeaponsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mPageNames[position];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(getIntent().getStringExtra(INVENTORY_ID_KEY)))
                .get(ManageInventoryViewModel.class);

        // Bind the ViewModel to XML
        ActivityManageInventoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_inventory);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        ButterKnife.bind(this);

        // Instantiate ViewPager and PagerAdapter
        mPagerAdapter = new InventoryItemsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(NUM_PAGES);

        // Set array of pages
        mPageNames = getResources().getStringArray(R.array.inventory);
        mTabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar and
        // enable the close button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24px);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_shopping_cart:
                showInventoryCart();
                break;
            case android.R.id.home:
                // Close activity
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInventoryCart() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment previousDialog = getSupportFragmentManager().findFragmentByTag("dialog");

        if (previousDialog != null) {
            fragmentTransaction.remove(previousDialog);
        }

        fragmentTransaction.addToBackStack(null);
        DialogFragment dialogFragment = InventoryCheckoutFragment.newInstance();
        dialogFragment.show(fragmentTransaction, "dialog");

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager iMm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            iMm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
