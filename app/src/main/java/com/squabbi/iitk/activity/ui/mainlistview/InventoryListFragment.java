package com.squabbi.iitk.activity.ui.mainlistview;


import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.InventoryListAdapter;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.KeyLocker;
import com.squabbi.iitk.model.Weapon;
import com.squabbi.iitk.util.Constants;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryListFragment extends Fragment {

    public static final String INVENTORY_REFERENCE_KEY = "inventory_ref";

    private MainActivityViewModel mViewModel;
    private InventoryListAdapter mAdapter;

    @BindView(R.id.inventory_recycler)
    RecyclerView mInventoryRecycler;

    public InventoryListFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {

        mAdapter = new InventoryListAdapter(mViewModel.getBaseInventoryFirestoreRecyclerBuilder()
        .setLifecycleOwner(this).build());

        mAdapter.setOnInventoryItemClickListener(new InventoryListAdapter.OnInventoryItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                openInventoryDetail(documentSnapshot);
            }
        });

        mInventoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mInventoryRecycler.setHasFixedSize(true);

        mInventoryRecycler.setAdapter(mAdapter);
    }

    private void openInventoryDetail(DocumentSnapshot documentSnapshot) {

        //Intent intent = new Intent(getContext(), )
        Toast.makeText(getContext(), documentSnapshot.getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ButterKnife.bind(this, view);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

        // Set up the Inventory RecyclerView
        initRecycler();

        return view;
    }

}
