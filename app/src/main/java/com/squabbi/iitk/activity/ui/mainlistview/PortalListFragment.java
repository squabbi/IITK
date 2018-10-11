package com.squabbi.iitk.activity.ui.mainlistview;

import android.content.Intent;
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

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.portal.view.PortalViewActivity;
import com.squabbi.iitk.adapter.OnFirestoreItemClickListener;
import com.squabbi.iitk.adapter.PortalListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment implements OnFirestoreItemClickListener {

    public static final String PORTAL_REFERENCE_KEY = "portal_ref";

    private MainActivityViewModel mViewModel;
    private PortalListAdapter mAdapter;

    @BindView(R.id.portal_recycler)
    RecyclerView mPortalRecycler;

    public PortalListFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {

        mAdapter = new PortalListAdapter(mViewModel.getBasePortalFirestoreRecyclerBuilder()
            .setLifecycleOwner(this).build());

        mAdapter.setOnItemClickListener(this);

        mPortalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mPortalRecycler.setHasFixedSize(true);

        mPortalRecycler.setAdapter(mAdapter);
    }

    private void openPortalDetail(DocumentSnapshot documentSnapshot) {

        Intent intent = new Intent(getContext(), PortalViewActivity.class);
        intent.putExtra(PORTAL_REFERENCE_KEY, documentSnapshot.getReference().getPath());
        startActivity(intent);
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

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        // Show new activity of Portal Details
        openPortalDetail(documentSnapshot);
    }
}
