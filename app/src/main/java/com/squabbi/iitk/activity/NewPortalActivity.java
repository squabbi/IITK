package com.squabbi.iitk.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.abdularis.piv.VerticalScrollParallaxImageView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import me.tankery.permission.PermissionRequestActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squabbi.iitk.R;
import com.squabbi.iitk.model.Portal;
import com.squabbi.iitk.util.Constants;

public class NewPortalActivity extends AppCompatActivity {
    @BindView(R.id.new_portal_toolbar) Toolbar mToolbar;
    @BindView(R.id.portal_name_et) EditText mPortalNameEt;
    @BindView(R.id.portal_notes_et) EditText mPortalNotesEt;

    private static final int PLACE_PICKER_REQUEST = 1;
    private static final int PERMISSION_CHECK_REQUEST = 2;

    private static final String[] PERMISSIONS_REQUIRED = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private ImagePicker mImagePicker = new ImagePicker();
    private Place mPlace;

    // Firebase
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_portal);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar and
        // enable the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchImagePicker(view);
            }
        });

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
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

    public void launchImagePicker(View view) {
        final String message = getString(R.string.permissions_camera);
        PermissionRequestActivity.start(this, PERMISSION_CHECK_REQUEST, PERMISSIONS_REQUIRED, message, message);
    }

    public void launchOcrActivity(View view) {

    }

    private boolean addPortal(String name, String notes) {
        // Validate entries

        // Make new portal object
        Portal portal = new Portal(name, mPlace.getLatLng(), notes);
        if (mAuth.getCurrentUser() != null) {
            mFirestore.collection(Constants.COLLECTION_AGENTS).document(mAuth.getUid()).collection(Constants.COLLECTION_PORTALS).add(portal)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(NewPortalActivity.this, "Portal saved", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewPortalActivity.this, "Portal failed to save", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return false;
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
                // Complete the action and add the Portal to the Database
                return addPortal(mPortalNameEt.getText().toString(), mPortalNotesEt.getText().toString());
            case android.R.id.home:
                // When the up button is pressed
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
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

        if (requestCode == PERMISSION_CHECK_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Launch image picker once permissions are granted
                mImagePicker.withActivity(this).start();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            //Add compression listener if withCompression is set to true
            mImagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) { // filePath of the compressed image
                    // Convert image to Bitmap
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                    // Set the image into the ParallaxImageView
                    VerticalScrollParallaxImageView portalVertParallaxIv = findViewById(R.id.vertParallaxImageView);
                    portalVertParallaxIv.setImageBitmap(selectedImage);
                }
            });

            String filePath = mImagePicker.getImageFilePath(data);
            if (filePath != null) {
                Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                VerticalScrollParallaxImageView portalVertParallaxIv = findViewById(R.id.vertParallaxImageView);
                portalVertParallaxIv.setImageBitmap(selectedImage);
            }
        }
    }
}
