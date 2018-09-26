package com.squabbi.iitk.model;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@IgnoreExtraProperties
public class Portal {

    private String mName;
    private String mNotes;
    @Nullable
    private GeoPoint mGeoPoint;
    private String mFriendlyLocation;
    private Integer mColour;
    @ServerTimestamp
    private Date mCreatedAt;

    // Required by Firebase
    Portal() {}

    /**
     * Basic Portal object to represent an Ingress portal.
     * @param name Name of the portal.
     * @param place Place object from Google Maps SDK (Portal's location).
     * @param notes Additional notes about the portal.
     * @param colour Colour tag of the portal in hexadecimal.
     */
    public Portal(@Nonnull String name, @Nullable Place place,
                  @Nullable String friendlyLocation, @Nonnull String notes, Integer colour) {

        this.mName = name;

        // Addresses
        // Coordinates
        if (place != null) {
            // Place has been selected
            // Extract LatLng from Place and create new GeoPoint
            LatLng latLng = place.getLatLng();
            this.mGeoPoint = new GeoPoint(latLng.latitude, latLng.longitude);
            // Set friendly address from Places
            this.mFriendlyLocation = place.getName().toString();
        }
        // If friendly location is not null or empty
        if (friendlyLocation != null && !friendlyLocation.isEmpty()) {
            this.mFriendlyLocation = friendlyLocation;
        }

        this.mNotes = notes;
        this.mColour = colour;
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

    public Integer getColour() {
        return mColour;
    }

    public void setColour(Integer colour) {
        mColour = colour;
    }

    @ServerTimestamp
    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }
}