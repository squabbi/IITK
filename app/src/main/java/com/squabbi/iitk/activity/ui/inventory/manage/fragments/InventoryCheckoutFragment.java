package com.squabbi.iitk.activity.ui.inventory.manage.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squabbi.iitk.R;
import com.squabbi.iitk._interface.OnInventoryItemClickListener;
import com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryViewModel;
import com.squabbi.iitk.adapter.InventoryCheckoutAdapter;
import com.squabbi.iitk.model.InventoryItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment sub-class which shows the contents of the current inventory cart and allows the user
 * to remove items from the cart.
 * This is a part of the {@link com.squabbi.iitk.activity.ui.inventory.manage.ManageInventoryActivity}
 * Use the {@link InventoryCheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class InventoryCheckoutFragment extends DialogFragment {

    private OnInventoryItemClickListener mListener;
    private ManageInventoryViewModel mViewModel;
    private InventoryCheckoutAdapter mAdapter;

    // View binds
    @BindView(R.id.checkout_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty_cart_linearlayout)
    LinearLayout mEmptyCartLl;

    @BindView(R.id.checkout_button)
    Button mCheckoutButton;

    @BindView(R.id.clear_cart_button)
    ImageButton mClearCartButton;

    // Required empty constructor
    public InventoryCheckoutFragment() {}

    /** Public factory method that creates a new instance of the fragment */
    public static InventoryCheckoutFragment newInstance() {
        return new InventoryCheckoutFragment();
    }

    private void initRecycler() {

        // Dividers
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new InventoryCheckoutAdapter(new InventoryCheckoutAdapter.OnModItemClickListener() {
            @Override
            public void onModClicked(InventoryItem item, int position) {
                // Handle clicks on mods (add them to ViewModel's basket)
                mListener.onItemSelected(item, position);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        // Swipe detection
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.removeItemFromInventoryCart(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(), R.string.removed_from_cart, Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(ManageInventoryViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventory_checkout, container, false);
        ButterKnife.bind(this, view);

        initRecycler();

        // Observe the InventoryList and update recycler view for changes
        mViewModel.getInventoryCheckoutLiveData().observe(getViewLifecycleOwner(), new Observer<List<InventoryItem>>() {
            @Override
            public void onChanged(List<InventoryItem> inventoryItems) {
                // Check the length, if 0, show a different page
                if (inventoryItems.size() > 0) {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mEmptyCartLl.setVisibility(View.GONE);
                    mCheckoutButton.setEnabled(true);
                    mClearCartButton.setEnabled(true);

                    mAdapter.setInventoryItemList(inventoryItems);
                } else {
                    // Hide recycler view and show empty cart view
                    mRecyclerView.setVisibility(View.GONE);
                    mEmptyCartLl.setVisibility(View.VISIBLE);
                    mCheckoutButton.setEnabled(false);
                    mClearCartButton.setEnabled(false);
                }
            }
        });

        return view;
    }

    @OnClick({R.id.clear_cart_button, R.id.checkout_button})
    void onClick(View view) {

        switch (view.getId()) {
            case R.id.clear_cart_button:
                // Show remove confirmation dialog
                confirmClearCart();
                break;
            case R.id.checkout_button:
                confirmCheckout();
                break;
        }
    }

    private void confirmCheckout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set title and message
        builder.setTitle(R.string.dialog_title_checkout)
                .setMessage(R.string.dialog_message_checkout);

        // Add the buttons
        builder.setPositiveButton(R.string.checkout, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                checkoutCart();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkoutCart() {

        mViewModel.checkoutCart();
    }

    private void confirmClearCart() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set title and message
        builder.setTitle(R.string.dialog_title_clear_cart)
                .setMessage(R.string.dialog_message_clear_cart);

        // Add the buttons
        builder.setPositiveButton(R.string.clear_all, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                clearCart();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearCart() {
        mViewModel.removeAllFromCart();
        Toast.makeText(getContext(), R.string.remove_all_from_cart, Toast.LENGTH_SHORT).show();
        // Close the dialog
        dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryItemClickListener) {
            mListener = (OnInventoryItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInventoryItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
