package com.squabbi.iitk.model;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class Portal {

    private String mName;
    private String mNotes;
    private GeoPoint mGeoPoint;
    private String mFriendlyLocation;
    private String mImageUrl;
    @ServerTimestamp
    private Date mCreatedAt;

    // Required by Firebase
    Portal() {}

    public Portal(String name, com.google.android.gms.maps.model.LatLng latLng, String notes, String imageUrl) {
        this.mName = name;
        //this.mImage = image;
        this.mGeoPoint = new GeoPoint(latLng.latitude, latLng.longitude);
        this.mNotes = notes;
        this.mImageUrl = imageUrl;
    }

    public Portal(String name, String location, String notes, String imageUrl) {
        this.mName = name;
        this.mFriendlyLocation = location;
        this.mNotes = notes;
        this.mImageUrl = imageUrl;
    }

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

    public String getFriendlyLocation() {
        return mFriendlyLocation;
    }

    public void setFriendlyLocation(String friendlyLocation) {
        mFriendlyLocation = friendlyLocation;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @ServerTimestamp
    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }
}