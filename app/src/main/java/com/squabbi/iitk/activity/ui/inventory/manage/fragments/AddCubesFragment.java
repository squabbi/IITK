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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCubesFragment extends Fragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    // Required empty constructor
    public AddCubesFragment() {}

    public static AddCubesFragment newInstance() {
        return new AddCubesFragment();
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

        // Power Cube items
        inventoryItems.add(new InventoryItem(Item.DetailItemType.LAWSON, R.string.power_cube_lawson, Item.Rarity.VERY_RARE, 0, R.drawable.power_cube_lawson));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 1, R.drawable.power_cube_l1));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 2, R.drawable.power_cube_l2));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 3, R.drawable.power_cube_l3));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 4, R.drawable.power_cube_l4));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 5, R.drawable.power_cube_l5));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 6, R.drawable.power_cube_l6));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 7, R.drawable.power_cube_l7));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.POWER_CUBE, R.string.power_cube_level, Item.Rarity.VERY_COMMON, 8, R.drawable.power_cube_l8));

        return inventoryItems;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
