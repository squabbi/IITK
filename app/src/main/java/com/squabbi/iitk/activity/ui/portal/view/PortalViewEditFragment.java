package com.squabbi.iitk.activity.ui.portal.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squabbi.iitk.databinding.FragmentPortalViewEditBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

public class PortalViewEditFragment extends Fragment {

    public static PortalViewEditFragment newInstance() { return new PortalViewEditFragment(); }

    private PortalViewViewModel mViewModel;

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
}
