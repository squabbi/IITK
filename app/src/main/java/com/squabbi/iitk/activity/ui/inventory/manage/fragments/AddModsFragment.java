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

public class AddModsFragment extends Fragment {

    private OnInventoryFragmentInteractionListener mListener;
    private ManageInventoryViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;


    @BindView(R.id.add_mods_recyclerview)
    RecyclerView mRecyclerView;

    public AddModsFragment() {
        // Required empty public constructor
    }

    public static AddModsFragment newInstance() {
        return new AddModsFragment();
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
        // ITO EN Transmuters
        inventoryItems.add(new InventoryItem(R.string.itoem_transmuter_plus, Item.Rarity.VERY_RARE, 0, R.drawable.itoen_transmuter_plus));
        inventoryItems.add(new InventoryItem(R.string.itoen_transmuter_minus, Item.Rarity.VERY_RARE, 0, R.drawable.itoem_transmuter_minus));
        // Link Amps
        inventoryItems.add(new InventoryItem(R.string.link_amp, Item.Rarity.RARE, 0, R.drawable.link_amp_rare));
        inventoryItems.add(new InventoryItem(R.string.link_amp, Item.Rarity.VERY_RARE, 0, R.drawable.link_amp_vr));
        inventoryItems.add(new InventoryItem(R.string.softbank_ultra_link, Item.Rarity.VERY_RARE, 0, R.drawable.link_amp_softbank));
        // Portal Shields
        inventoryItems.add(new InventoryItem(R.string.shield, Item.Rarity.COMMON, 0, R.drawable.shield_common));
        inventoryItems.add(new InventoryItem(R.string.shield, Item.Rarity.RARE, 0, R.drawable.shield_rare));
        inventoryItems.add(new InventoryItem(R.string.shield, Item.Rarity.VERY_RARE, 0, R.drawable.shield_vr));
        inventoryItems.add(new InventoryItem(R.string.shield_aegis, Item.Rarity.VERY_RARE, 0, R.drawable.shield_aegis));
        // Heat Sinks
        inventoryItems.add(new InventoryItem(R.string.heat_sink, Item.Rarity.COMMON, 0, R.drawable.heat_sink_common));
        inventoryItems.add(new InventoryItem(R.string.heat_sink, Item.Rarity.RARE, 0, R.drawable.heat_sink_rare));
        inventoryItems.add(new InventoryItem(R.string.heat_sink, Item.Rarity.VERY_RARE, 0, R.drawable.heat_sink_vr));
        // Force Amp
        inventoryItems.add(new InventoryItem(R.string.force_amp, Item.Rarity.RARE, 0, R.drawable.force_amp));
        // Multi-hacks
        inventoryItems.add(new InventoryItem(R.string.multi_hack, Item.Rarity.COMMON, 0, R.drawable.multi_hack_common));
        inventoryItems.add(new InventoryItem(R.string.multi_hack, Item.Rarity.RARE, 0, R.drawable.multi_hack_rare));
        inventoryItems.add(new InventoryItem(R.string.multi_hack, Item.Rarity.VERY_RARE, 0, R.drawable.multi_hack_vr));
        // Turret
        inventoryItems.add(new InventoryItem(R.string.turret, Item.Rarity.RARE, 0, R.drawable.turret));

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
        View view = inflater.inflate(R.layout.fragment_add_mods, container, false);
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
