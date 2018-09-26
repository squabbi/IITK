package com.squabbi.iitk.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.PortalAdapter;
import com.squabbi.iitk.viewmodel.MainActivityViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment implements PortalAdapter.OnPortalSelectedListener {

    private static final String TAG = "PortalListFragment";
    private MainActivityViewModel mViewModel;
    private PortalAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Moved ViewModel stuff from here to onStart
        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
    }

    @BindView(R.id.portal_recycler)
    RecyclerView mPortalRecycler;

    public PortalListFragment() {
        // Required empty public constructor
    }

    private void initRecycler() {
        mAdapter = new PortalAdapter(this);

        mPortalRecycler.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        mPortalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mPortalRecycler.setHasFixedSize(true);
        mPortalRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onPortalSelected(DocumentSnapshot portal) {
        // TODO: Go into the details page for the selected portal
        // Show a snackbar on errors
        if (getView() != null) {
            Snackbar.make(getView().findViewById(R.id.portal_view_root_layout),
                    "Tapped on: " + portal.getId(), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portal_list, container, false);
        ButterKnife.bind(this, view);

        initRecycler();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mViewModel.getPortalDocumentChange().observe(this, new Observer<List<DocumentSnapshot>>() {
            @Override
            public void onChanged(List<DocumentSnapshot> documentSnapshots) {
                mAdapter.setDocChangeData(documentSnapshots);
            }
        });
    }

    @Override
    public void onStop() {
        //PortalAdapter.clearSnapshots();
        super.onStop();
    }
}
