package com.squabbi.iitk.activity.ui.mainlistview;

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
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.PortalAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment {

    private static final String TAG = "PortalListFragment";
    private MainActivityViewModel mViewModel;
    private PortalAdapter mAdapter;

    @BindView(R.id.portal_recycler)
    RecyclerView mPortalRecycler;

    public PortalListFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {

        mAdapter = new PortalAdapter(mViewModel.getBaseFirestoreRecyclerBuilder()
            .setLifecycleOwner(this).build());

        mAdapter.setOnItemClickListener(new PortalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(getActivity(), "Selected :: " + documentSnapshot.getId(), Toast.LENGTH_LONG).show();
            }
        });

        mPortalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mPortalRecycler.setHasFixedSize(true);

        mPortalRecycler.setAdapter(mAdapter);
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
