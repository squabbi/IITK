package com.squabbi.iitk.activity.ui.inventory.manage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnInventoryItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryViewModel;
import com.squabbi.iitk.adapter.InventoryItemAdapter;
import com.squabbi.iitk.model.InventoryItem;
import com.squabbi.iitk.model.Item;

import java.util.LinkedList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment sub-class that shows a list of Resonators by level and allows the user
 * to add the selected item to the inventory cart.
 * This is a part of the {@link com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity}
 * Activities that contain this fragment must implement the
 * {@link OnInventoryItemClickListener} interface
 * to handle interaction events.
 * Use the {@link AddResonatorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddResonatorsFragment extends Fragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    // Empty public constructor
    public AddResonatorsFragment() {

    }

    /** Public factory method which creates a new instance of the fragment */
    public static AddResonatorsFragment newInstance() {
        return new AddResonatorsFragment();
    }

    private void initRecycler() {

        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new InventoryItemAdapter(getItems(), new InventoryItemAdapter.OnModItemClickListener() {
            @Override
            public void onModClicked(InventoryItem item, int position) {
                // Handle clicks on mods (add them to ViewModel's basket)
                mListener.onItemSelected(item, position);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    private List<InventoryItem> getItems() {

        List<InventoryItem> inventoryItems = new LinkedList<>();

        // Resonators
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 1, R.drawable.resonator_l1));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 2, R.drawable.resonator_l2));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 3, R.drawable.resonator_l3));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 4, R.drawable.resonator_l4));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 5, R.drawable.resonator_l5));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 6, R.drawable.resonator_l6));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 7, R.drawable.resonator_l7));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.RESONATOR, R.string.resonator_level, Item.Rarity.VERY_COMMON, 8, R.drawable.resonator_l8));

        return inventoryItems;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(ManageInventoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_recycler, container, false);
        ButterKnife.bind(this, view);

        initRecycler();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryItemClickListener) {
            mListener = (OnInventoryItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInventoryItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
