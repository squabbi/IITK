package com.squabbi.iitk.activity.ui.mainlistview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFirestoreItemClickListener;
import com.squabbi.iitk.adapter.PortalListAdapter;

/**
 * A fragment sub-class for dispaying a list of user's portals in a RecyclerView.
 * This fragment's parent must implement an {@link OnFirestoreItemClickListener}.
 */
public class PortalListFragment extends Fragment {

    private MainActivityViewModel mViewModel;
    private PortalListAdapter mAdapter;
    private OnFirestoreItemClickListener mListner;

    @BindView(R.id.portal_recycler)
    RecyclerView mPortalRecycler;

    /**
     * Empty constructor for PortalListFragment
     */
    public PortalListFragment() {}

    private void initRecycler() {

        mAdapter = new PortalListAdapter(mViewModel.getBasePortalFirestoreRecyclerBuilder()
                .setLifecycleOwner(this).build());

        mAdapter.setOnItemClickListener(mListner);

        mPortalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mPortalRecycler.setHasFixedSize(true);

        mPortalRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFirestoreItemClickListener) {
            mListner = (OnFirestoreItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFirestoreItemClickListeners");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portal_list, container, false);
        ButterKnife.bind(this, view);

        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

        initRecycler();

        return view;
    }
}
