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

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewActivity;
import com.squabbi.iitk.adapter.InventoryListAdapter;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryListFragment extends Fragment implements OnFirestoreItemClickListener {

    public static final String INVENTORY_PATH_KEY = "inventory_path";
    public static final String INVENTORY_ID_KEY = "inventory_id";

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

        mAdapter.setOnInventoryItemClickListener(this);

        mInventoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mInventoryRecycler.setHasFixedSize(true);

        mInventoryRecycler.setAdapter(mAdapter);
    }

    private void openInventoryDetail(DocumentSnapshot documentSnapshot) {

        Intent intent = new Intent(getContext(), InventoryViewActivity.class);
        intent.putExtra(INVENTORY_PATH_KEY, documentSnapshot.getReference().getPath());
        intent.putExtra(INVENTORY_ID_KEY, documentSnapshot.getReference().getId());
        startActivity(intent);
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
    public void onFirestoreItemClick(DocumentSnapshot documentSnapshot, int position) {
        // Opens inventory detail activity
        openInventoryDetail(documentSnapshot);
    }
}
