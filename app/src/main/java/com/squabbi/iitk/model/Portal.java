package com.squabbi.iitk.model;

import android.media.Image;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.type.LatLng;

@IgnoreExtraProperties
public class Portal {

//    public static final String FIELD_NAME = "mName";
//    public static final String FIELD_LOCATION = "mLongLat";
//    public static final String FIELD_NOTES = "mNotes";

    private String mName;
    private String mNotes;
    private GeoPoint mGeoPoint;
    //private Image mImage;


    Portal() {}

    public Portal(String name, com.google.android.gms.maps.model.LatLng latLng, String notes) {
        this.mName = name;
        //this.mImage = image;
        this.mGeoPoint = new GeoPoint(latLng.latitude, latLng.longitude);
        this.mNotes = notes;
    }

//    public Image getImage() {
//        return mImage;
//    }
//
//    public void setImage(Image image) {
//        mImage = image;
//    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public GeoPoint getGeoPoint() {
        return mGeoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mGeoPoint = geoPoint;
    }
}