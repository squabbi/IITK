package com.squabbi.iitk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.github.abdularis.piv.VerticalScrollParallaxImageView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;

import com.squabbi.iitk.R;
import com.squabbi.iitk.viewmodel.NewPortalViewModel;

public class NewPortalActivity extends AppCompatActivity {
    @BindView(R.id.new_portal_toolbar) Toolbar mToolbar;
    @BindView(R.id.portal_name_et) EditText mPortalNameEt;
    @BindView(R.id.portal_notes_et) EditText mPortalNotesEt;
    @BindView(R.id.portal_location_et) EditText mPortalLocationEt;

    private NewPortalViewModel mViewModel;
    private Place mPlace;
    private Integer mColour;

    private static final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_portal);
        ButterKnife.bind(this);

        // Set ViewModel
        mViewModel = ViewModelProviders.of(this).get(NewPortalViewModel.class);
        // Observe changes to ColourPicker LiveData
        mViewModel.getColourLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer colour) {
                mColour = colour;
            }
        });

        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar and
        // enable the close button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                // Complete the action and add the Portal to the Database.
                // Get strings
                String name = mPortalNameEt.getText().toString();
                String notes = mPortalNotesEt.getText().toString();
                Place place = mPlace;
                String friendlyLocation = mPortalLocationEt.getText().toString();
                Integer colour = mColour;

                // Close the keyboard and submit strings to ViewModel.
                closeKeyboard();

                if (!mViewModel.addPortal(name, place, friendlyLocation, notes, colour)) {
                    // If adding was not successful
                }
            default:
                // Invoke super for all other items.
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager iMm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            iMm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                mPlace = PlacePicker.getPlace(this, data);
                EditText et = findViewById(R.id.portal_location_et);
                // Display friendly Place name
                et.setText(String.valueOf(mPlace.getName()));
            }
        }
    }
}
