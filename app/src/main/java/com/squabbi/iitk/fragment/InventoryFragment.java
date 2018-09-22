package com.squabbi.iitk.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squabbi.iitk.R;
import com.squabbi.iitk.util.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;


    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        Query query = mFirestore.collection(Constants.COLLECTION_AGENTS).document(mAuth.getUid()).collection(Constants.COLLECTION_PORTALS);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);

        TextView text = view.findViewById(R.id.inv_text);



       // text.setText(query.get().);

        return view;
    }

}
