package com.squabbi.iitk.activity.ui.inventory.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Item;

/**
 * ItemView fragment used to view the details of the selected item.
 * Use the {@link ItemViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemViewFragment extends Fragment {

    InventoryViewViewModel mViewModel;

    /** Public factory method that returns a new instance of the fragment */
    public static ItemViewFragment newInstance() {
        return new ItemViewFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(InventoryViewViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_view, container, false);
    }

}
