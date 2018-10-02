package com.squabbi.iitk.activity.ui.mainlistview;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squabbi.iitk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryListFragment extends Fragment {

    public InventoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        TextView text = view.findViewById(R.id.inv_text);

        return view;
    }

}
