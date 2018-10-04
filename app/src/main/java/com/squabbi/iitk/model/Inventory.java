package com.squabbi.iitk.model;

import java.util.List;

public class Inventory {

    private String mName;
    private List<Item> mInventoryContents;

    // Empty constructor required by Firebase
    public Inventory() {}

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
