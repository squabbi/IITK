package com.squabbi.iitk.activity.ui.inventory.manage.fragments.weapons;

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

public class AddFlipCardsFragment extends Fragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    // Required empty constructor
    public AddFlipCardsFragment() {}

    public static AddFlipCardsFragment newInstance() {
        return new AddFlipCardsFragment();
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
        inventoryItems.add(new InventoryItem(Item.DetailItemType.ADA, R.string.flip_ada, Item.Rarity.VERY_RARE, 0, R.drawable.flip_ada));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.JARVIS, R.string.flip_jarvis, Item.Rarity.VERY_RARE, 0, R.drawable.flip_jarvis));

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
