package com.squabbi.iitk.activity.ui.inventory.view.categories;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk._interface.OnInventoryItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.view.InventoryViewViewModel;
import com.squabbi.iitk.adapter.InventoryItemListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment shows a list of all items in the Inventory.
 * Activities that contain this fragment must implement the
 * {@link OnInventoryItemClickListener} & {@link OnFragmentViewInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemAllFragment extends Fragment {

    private OnFragmentViewInteractionListener mListener;
    private OnFirestoreItemClickListener mFirestoreListener;
    private InventoryViewViewModel mViewModel;
    private InventoryItemListAdapter mAdapter;

    @BindView(R.id.ingress_item_recyclerview)
    RecyclerView mRecyclerView;

    /** Required empty public constructor */
    public ItemAllFragment() {}

    /** Public factory method returns a new instance of the fragment */
    public static ItemAllFragment newInstance() {
        return new ItemAllFragment();
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
        if (context instanceof OnFragmentViewInteractionListener) {
            mListener = (OnFragmentViewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInventoryItemClickListener");
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
}
