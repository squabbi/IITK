package com.squabbi.iitk.activity.ui.inventory.manage.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.weapons.AddFlipCardsFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.weapons.AddUltraStrikesFragment;
import com.squabbi.iitk.activity.ui.inventory.manage.fragments.weapons.AddXmpsFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment sub-class that shows a TabView of Weapons and allows the user to browse between
 * the various weapons (Flip Cards, Xmps and Ultra Strikes to add the selected item to the inventory cart.
 * This is a part of the {@link com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity}
 * Use the {@link AddWeaponsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddWeaponsFragment extends Fragment {

    private static final int NUM_PAGES = 3;
    private String[] mPageNames;
    private PagerAdapter mPagerAdapter;

    @BindView(R.id.add_inventory_weapons_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.add_inventory_weapons_tablayout)
    TabLayout mTabLayout;

    // Required public constructor
    public AddWeaponsFragment() {}

    /** Public factory method that returns a new instance of the fragment */
    public static AddWeaponsFragment newInstance() {
        return new AddWeaponsFragment();
    }

    private void initTabView() {

        // Instantiate ViewPager and PagerAdapter
        mPagerAdapter = new WeaponsItemsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // Set array of pages
        mPageNames = getResources().getStringArray(R.array.weapons);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class WeaponsItemsPagerAdapter extends FragmentStatePagerAdapter {

        public WeaponsItemsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AddXmpsFragment.newInstance();
                case 1:
                    return AddUltraStrikesFragment.newInstance();
                case 2:
                    return AddFlipCardsFragment.newInstance();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_weapons, container, false);
        ButterKnife.bind(this, view);

        initTabView();

        return view;
    }
}
