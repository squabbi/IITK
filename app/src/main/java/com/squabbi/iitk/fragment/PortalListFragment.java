package com.squabbi.iitk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.NewPortalActivity;

import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment {
    @OnClick(R.id.openPortal_btn)
    public void openPortal(View view) {
        Intent intent = new Intent(getContext().getApplicationContext(), NewPortalActivity.class);
        startActivity(intent);
    }

    public PortalListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_list, container, false);
    }

}
