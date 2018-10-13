package com.squabbi.iitk.activity.ui.inventory.manage.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInventoryItemClickListener} interface
 * to handle interaction events.
 * Use the {@link AddCapsulesKeysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCapsulesKeysFragment extends Fragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    public AddCapsulesKeysFragment() {
        // Required empty public constructor
    }

    public static AddCapsulesKeysFragment newInstance() {
        return new AddCapsulesKeysFragment();
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

        // Capsules and Key items
        inventoryItems.add(new InventoryItem(Item.DetailItemType.CAPSULE, R.string.capsule, Item.Rarity.RARE, 0, R.drawable.capsule));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.QUANTUM, R.string.capsule_quantum, Item.Rarity.VERY_RARE, 0, R.drawable.capsule_quantum));
        inventoryItems.add(new InventoryItem(Item.DetailItemType.PORTAL_KEY, R.string.portal_key, Item.Rarity.VERY_COMMON, 0, R.drawable.portal_key));

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
