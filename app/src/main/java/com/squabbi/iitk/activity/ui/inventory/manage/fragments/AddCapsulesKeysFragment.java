package com.squabbi.iitk.activity.ui.inventory.manage.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryViewModel;
import com.squabbi.iitk.activity.ui.inventory.manage.OnInventoryFragmentInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInventoryFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCapsulesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCapsulesFragment extends Fragment {

    private OnInventoryFragmentInteractionListener mListener;
    private ManageInventoryViewModel mViewModel;

    public AddCapsulesFragment() {
        // Required empty public constructor
    }

    public static AddCapsulesFragment newInstance() {
        return new AddCapsulesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(ManageInventoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_capsules, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryFragmentInteractionListener) {
            mListener = (OnInventoryFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInventoryFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
