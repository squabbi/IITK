package com.squabbi.iitk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment {

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
