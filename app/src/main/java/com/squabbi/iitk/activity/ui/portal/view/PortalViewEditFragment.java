package com.squabbi.iitk.activity.ui.portal.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnFragmentViewInteractionListener;
import com.squabbi.iitk.databinding.FragmentPortalViewEditBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

/**
 * Fragment that provides edit controls for the current portal in view.
 */

public class PortalViewEditFragment extends Fragment {

    public static PortalViewEditFragment newInstance() { return new PortalViewEditFragment(); }

    private PortalViewViewModel mViewModel;
    private OnFragmentViewInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(PortalViewViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentPortalViewEditBinding binding = FragmentPortalViewEditBinding
                .inflate(inflater, container, false);
        ButterKnife.bind(this, binding.getRoot());

        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // Show confirmation dialog.
                mListener.onMenuItemPressed(item);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentViewInteractionListener) {
            mListener = (OnFragmentViewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement OnFragmentViewInteractionListener");
        }
    }
}
