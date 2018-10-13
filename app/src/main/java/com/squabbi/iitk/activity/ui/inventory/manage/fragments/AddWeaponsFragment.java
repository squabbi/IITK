package com.squabbi.iitk.activity.ui.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.weapons.AddUltraStrikeFragment;
import com.squabbi.iitk.activity.ui.inventory.weapons.AddXmpFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

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
                    return AddXmpFragment.newInstance();
                case 1:
                    return AddUltraStrikeFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
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
