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
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemViewFragment extends Fragment {

    InventoryViewViewModel mViewModel;

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
