package com.squabbi.iitk.activity.ui.newportal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

import com.google.android.material.textfield.TextInputLayout;
import com.kunzisoft.androidclearchroma.listener.OnColorSelectedListener;
import com.squabbi.iitk.R;
import com.squabbi.iitk.databinding.ActivityNewPortalBinding;

public class NewPortalActivity extends AppCompatActivity implements OnColorSelectedListener {

    private NewPortalViewModel mViewModel;
    private Place mPlace;
    private Integer mColour;

    private static final String TAG_CHROMA_DIALOG = "TAG_CHROMA_DIALOG";
    private static final int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.new_portal_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.portal_name_inputlayout)
    TextInputLayout mNameInputLayout;

    @BindView(R.id.portal_name_et)
    EditText mPortalNameEt;

    @OnTextChanged(value = R.id.portal_name_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void nameTextEntered(Editable editable) {
        mNameInputLayout.setErrorEnabled(false);
    }

    @BindView(R.id.portal_notes_et)
    EditText mPortalNotesEt;

    @BindView(R.id.portal_friendly_location_inputlayout)
    TextInputLayout mFriendlyLocationInputLayout;

    @BindView(R.id.portal_friendly_location_et)
    EditText mPortalFriendlyLocationEt;

    @OnTextChanged(value = R.id.portal_name_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void friendlyLocationTextEntered() {
        mFriendlyLocationInputLayout.setErrorEnabled(false);
    }

    @BindView(R.id.colour_bar_view)
    View mColourBarView;

    @BindView(R.id.new_portal_fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(NewPortalViewModel.class);

        // Bind the viewmodel to view.
        ActivityNewPortalBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_portal);
        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);

        ButterKnife.bind(this);

        // Observe changes to ColourPicker LiveData
        mViewModel.getColourLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer colour) {
                mColour = colour;
                ColorDrawable colorDrawable = new ColorDrawable(colour);
                mColourBarView.setBackground(colorDrawable);
            }
        });

        // Observe changes to Place LiveData
        mViewModel.getPlaceLiveData().observe(this, new Observer<Place>() {
            @Override
            public void onChanged(Place place) {
                mPlace = place;
            }
        });

        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar and
        // enable the close button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24px);

       mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.getChromaDialogBuilder()
                        .create()
                        .show(getSupportFragmentManager(), TAG_CHROMA_DIALOG);
            }
        });

    }

    public void launchPlacePicker(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                // Close the keyboard and submit strings to ViewModel.
                closeKeyboard();
                // Validate entries
                if (validateText()) {
                    // Add portal and exit activity
                    addPortal();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager iMm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            iMm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean validateText() {
        boolean passingStatus = true;

        // Check for empty name
        if (isEmpty(mPortalNameEt)) {
            mNameInputLayout.setError(getString(R.string.inputlayout_error_emptyname));
            mNameInputLayout.setErrorEnabled(true);
            passingStatus = false;
        }

        // Check for empty friendly location
        if (isEmpty(mPortalFriendlyLocationEt)) {
            mFriendlyLocationInputLayout.setError(getString(R.string.inputlayout_error_emptyfriendlylocation));
            mFriendlyLocationInputLayout.setErrorEnabled(true);
            passingStatus = false;
        }

        return passingStatus;
    }

    private void addPortal() {

        // Complete the action and add the Portal to the Database.
        // Get strings from textviews
        String name = mPortalNameEt.getText().toString();
        String notes = mPortalNotesEt.getText().toString();
        Place place = mPlace;
        String friendlyLocation = mPortalFriendlyLocationEt.getText().toString();
        Integer colour = mColour;

        mViewModel.addPortal(name, place, friendlyLocation, notes, colour);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                mViewModel.setPlaceLiveData(PlacePicker.getPlace(this, data));
                // Display friendly Place name only if it is empty
                if (isEmpty(mPortalFriendlyLocationEt)) {
                    mPortalFriendlyLocationEt.setText(String.valueOf(mPlace.getName()));
                }
                mViewModel.setPortalLocationLiveData(mPlace.getLatLng().toString());
            }
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    public void onPositiveButtonClick(int color) {
        // Set the LiveData
        mViewModel.setColourLiveData(color);
    }

    @Override
    public void onNegativeButtonClick(int color) {
        // Do nothing.
    }
}
