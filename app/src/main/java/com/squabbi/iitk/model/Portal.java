package com.squabbi.iitk.model;

import android.media.Image;

import com.google.android.gms.location.places.Place;

public class Portal {
    private String mName;
    private Image mImage;
    private Place mPlace;
    // Store location here

    Portal(String name, Image image, Place place) {
        this.mName = name;
        this.mImage = image;
        this.mPlace = place;
    }
}
