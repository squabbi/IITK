package com.squabbi.iitk.activity.ui.inventory.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.adapter.InventoryItemListAdapter;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.databinding.FragmentInventoryItemListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentViewInteractionListener} interface
 * to handle interaction events.
 */
public class ItemListViewFragment extends Fragment implements OnFirestoreItemClickListener, OnFragmentViewInteractionListener {

    private OnFragmentViewInteractionListener mListener;
    private OnFirestoreItemClickListener mFirestoreListener;
    private InventoryViewViewModel mViewModel;
    private InventoryItemListAdapter mAdapter;

    @BindView(R.id.inventory_items_recycler)
    RecyclerView mRecyclerView;

    public static ItemListViewFragment newInstance() { return new ItemListViewFragment(); }

    public ItemListViewFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {

        mAdapter = new InventoryItemListAdapter(mViewModel.getBaseItemFirestoreRecyclerBuilder()
            .setLifecycleOwner(this).build(), getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(InventoryViewViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentInventoryItemListBinding binding = FragmentInventoryItemListBinding
                .inflate(inflater, container, false);
        ButterKnife.bind(this, binding.getRoot());

        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);

        initRecycler();

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentViewInteractionListener) {
            mListener = (OnFragmentViewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentViewInteractionListener");
        }

        if (context instanceof OnFirestoreItemClickListener) {
            mFirestoreListener = (OnFirestoreItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + " must implement OnFirestoreItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mFirestoreListener = null;
    }

    @OnClick(R.id.inventory_item_fab)
    void onClick(View view) {
        onViewPressed(view);
    }

    @Override
    public void onViewPressed(View view) {
        mListener.onViewPressed(view);
    }

    @Override
    public void onFirestoreItemClick(DocumentSnapshot documentSnapshot, int position) {
        mFirestoreListener.onFirestoreItemClick(documentSnapshot, position);
    }
}
