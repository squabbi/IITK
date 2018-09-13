package com.squabbi.iitk.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.LoginActivity;
import com.squabbi.iitk.activity.NewPortalActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortalListFragment extends Fragment implements View.OnClickListener {

    private Button mOpenPortal;
    private Button mOpenLogin;

    public PortalListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_portal_list, container, false);
        mOpenPortal = view.findViewById(R.id.openPortal_btn);
        mOpenLogin = view.findViewById(R.id.openLogin_btn);

        mOpenPortal.setOnClickListener(this);
        mOpenLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openPortal_btn:
                startActivity(new Intent(getContext(), NewPortalActivity.class));
                break;
            case R.id.openLogin_btn:
                startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }
}
