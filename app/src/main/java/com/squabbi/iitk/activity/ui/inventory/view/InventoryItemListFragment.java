package com.squabbi.iitk.activity.ui.inventory.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.InventoryItemListAdapter;
import com.squabbi.iitk.databinding.FragmentInventoryItemListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryItemListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class InventoryItemListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private InventoryViewViewModel mViewModel;
    private InventoryItemListAdapter mAdapter;

    @BindView(R.id.inventory_items_recycler)
    RecyclerView mRecyclerView;

    public static InventoryItemListFragment newInstance() { return new InventoryItemListFragment(); }

    public InventoryItemListFragment() {
        // Required empty public constructor
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

        return binding.getRoot();
    }

    public void onInventoryItemSelected(DocumentSnapshot documentSnapshot, int position) {
        if (mListener != null) {
            mListener.onItemSelected(documentSnapshot, position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Interface for getting the document
     */
    public interface OnFragmentInteractionListener {
        void onItemSelected(DocumentSnapshot documentSnapshot, int position);
    }
}
