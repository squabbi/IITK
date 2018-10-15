package com.squabbi.iitk.activity.ui.mainlistview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.firestore.ChangeEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewActivity;
import com.squabbi.iitk.adapter.InventoryListAdapter;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;


/**
 * Fragment sub-class for displaying the list of inventories associated with the current user
 * that is logged in.
 * The parent activity must implement {@link OnFirestoreItemClickListener} to handle interactions with
 * Firestore based items from within RecyclerViews.
 */

public class InventoryListFragment extends Fragment {

    private MainActivityViewModel mViewModel;
    private InventoryListAdapter mAdapter;
    private OnFirestoreItemClickListener mListener;

    @BindView(R.id.inventory_recycler)
    RecyclerView mInventoryRecycler;

    @BindView(R.id.empty_inventory_list_textview)
    TextView mEmptyInventoryTv;

    /** Empty constructor for Fragment */
    public InventoryListFragment() {}

    private void initRecycler() {

        mAdapter = new InventoryListAdapter(mViewModel.getBaseInventoryFirestoreRecyclerBuilder()
        .setLifecycleOwner(this).build());

        mAdapter.setOnItemClickListener(mListener);

        mInventoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mInventoryRecycler.setHasFixedSize(true);

        mInventoryRecycler.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ButterKnife.bind(this, view);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // Set up the Inventory RecyclerView
        initRecycler();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFirestoreItemClickListener) {
            mListener = (OnFirestoreItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFirestoreItemClickListeners");
        }
    }
}
