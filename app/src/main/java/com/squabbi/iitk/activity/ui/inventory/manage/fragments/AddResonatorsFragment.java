package com.squabbi.iitk.activity.ui.inventory;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squabbi.iitk.R;
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

public class AddResonatorsFragment extends Fragment {

    private OnInventoryFragmentInteractionListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.add_resonators_recyclerview)
    RecyclerView mRecyclerView;

    // Empty public constructor
    public AddResonatorsFragment() {

    }

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
            public void onModClicked(int position) {
                // Handle clicks on mods (add them to ViewModel's basket)
                Toast.makeText(getContext(), "You touched item: " + position + ". That is: " + getContext().getString(getItems().get(position).getNameResource()), Toast.LENGTH_LONG).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    private List<InventoryItem> getItems() {

        List<InventoryItem> inventoryItems = new LinkedList<>();

        // Resonators
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 1, R.drawable.resonator_l1));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 2, R.drawable.resonator_l2));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 3, R.drawable.resonator_l3));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 4, R.drawable.resonator_l4));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 5, R.drawable.resonator_l5));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 6, R.drawable.resonator_l6));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 7, R.drawable.resonator_l7));
        inventoryItems.add(new InventoryItem(R.string.resonator_level, Item.Rarity.VERY_COMMON, 8, R.drawable.resonator_l8));

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
        View view = inflater.inflate(R.layout.fragment_add_resonators, container, false);
        ButterKnife.bind(this, view);

        initRecycler();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryFragmentInteractionListener) {
            mListener = (OnInventoryFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInventoryFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
