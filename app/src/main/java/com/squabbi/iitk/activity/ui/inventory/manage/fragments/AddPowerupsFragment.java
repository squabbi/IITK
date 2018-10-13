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

public class AddStoreItemsFragment extends Fragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    public AddStoreItemsFragment() {
        // Required empty public constructor
    }

    public static AddStoreItemsFragment newInstance() {
        return new AddStoreItemsFragment();
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

        // Key Lockers
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_GREEN, R.string.locker_green, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_green));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_BLUE, R.string.locker_blue, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_blue));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_WHITE, R.string.locker_white, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_white));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_RED, R.string.locker_red, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_red));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_YELLOW, R.string.locker_yellow, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_yellow));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LOCKER_ANNIVERSARY, R.string.locker_anniversary, Item.Rarity.VERY_RARE, 0, R.drawable.key_locker_anniversary));

        // Fracker
        inventoryItems.add(new InventoryItem(Item.DetailItemType.FRACKER, R.string.portal_fracker, Item.Rarity.VERY_RARE, 0, R.drawable.portal_fracker));

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
