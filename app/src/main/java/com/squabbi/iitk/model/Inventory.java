package com.squabbi.iitk.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

public class Inventory {

    private String mName;
    private String mDescription;
    private Integer mColour;
    @ServerTimestamp
    private Date mCreatedAt;

    // Empty constructor required by Firebase
    Inventory() {}

    public Inventory(@NonNull String name, @NonNull String description,
                     Integer colour) {

        this.mName = name;
        this.mDescription = description;
        this.mColour = colour;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Integer getColour() {
        return mColour;
    }

    public void setColour(Integer color) {
        mColour = color;
    }

    @ServerTimestamp
    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }
}
