package com.squabbi.iitk.fragment;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.squabbi.iitk.R;
import com.squabbi.iitk.adapter.PortalAdapter;
import com.squabbi.iitk.util.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment implements View.OnClickListener, PortalAdapter.OnPortalSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private Query mQuery;
    private PortalAdapter mAdapter;

    @BindView(R.id.portal_recycler)
    RecyclerView mPortalRecycler;

    public PortalListFragment() {
        // Required empty public constructor
    }

    private void initFirestore() {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection(Constants.COLLECTION_AGENTS).document(mAuth.getUid()).collection(Constants.COLLECTION_PORTALS);

        mPortalRecycler.setBackgroundColor(Color.CYAN);
    }

    private void initRecycler() {
        mAdapter = new PortalAdapter(mQuery, this) {
            @Override
            protected void onDataChanged() {
                // TODO: Implement view when the list is empty
                super.onDataChanged();
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                if (getView() != null) {
                    Snackbar.make(getView().findViewById(R.id.portal_view_root_layout),
                            "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
                }
            }
        };

        mPortalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mPortalRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onPortalSelected(DocumentSnapshot portal) {
        // TODO: Go into the details page for the selected portal
        // Show a snackbar on errors
        if (getView() != null) {
            Snackbar.make(getView().findViewById(R.id.portal_view_root_layout),
                    "Tapped on: " + portal.getId(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Start listening
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // Stop listening
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portal_list, container, false);
        ButterKnife.bind(this, view);
//        mOpenPortal = view.findViewById(R.id.openPortal_btn);
//        mOpenLogin = view.findViewById(R.id.openLogin_btn);
//
//        mOpenPortal.setOnClickListener(this);
//        mOpenLogin.setOnClickListener(this);

        FirebaseFirestore.setLoggingEnabled(true);

        initFirestore();
        initRecycler();

        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.openPortal_btn:
//                startActivity(new Intent(getContext(), NewPortalActivity.class));
//                break;
//            case R.id.openLogin_btn:
//                startActivity(new Intent(getContext(), LoginActivity.class));
//        }
    }
}
