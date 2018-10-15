package com.squabbi.iitk.activity.ui.inventory.view;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;

/**
 * Fragment shows a list of all items in the Inventory.
 * Activities that contain this fragment must implement the
 * {@link OnInventoryItemClickListener} & {@link OnFragmentViewInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CapsuleViewFragment extends Fragment {

    private InventoryViewViewModel mViewModel;

    public static CapsuleViewFragment newInstance() {
        return new CapsuleViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.capsule_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InventoryViewViewModel.class);
        // TODO: Use the ViewModel
    }

}
