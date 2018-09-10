package com.squabbi.iitk.model;

import android.media.Image;

public class Portal {
    private String mName;
    private Image mImage;
    // Store location here

    Portal(String name, Image image) {
        this.mName = name;
        this.mImage = image;
    }
}
