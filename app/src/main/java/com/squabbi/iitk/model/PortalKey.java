package com.squabbi.iitk.model;

import com.google.firebase.firestore.DocumentReference;

public class PortalKey extends Item {

    private String mPortalName;
    private String mPortalLocation;
    private DocumentReference mPortalReference;

    // Required empty public constructor for Firebase
    public PortalKey() {}

    public PortalKey(String name, String location, DocumentReference portalReference) {
        super(ItemType.KEY, Rarity.VERY_COMMON, 0);

        this.mPortalName = name;
        this.mPortalLocation = location;
        this.mPortalReference = portalReference;
    }

    public String getPortalName() {
        return mPortalName;
    }

    public void setPortalName(String portalName) {
        mPortalName = portalName;
    }

    public String getPortalLocation() {
        return mPortalLocation;
    }

    public void setPortalLocation(String portalLocation) {
        mPortalLocation = portalLocation;
    }

    public DocumentReference getPortalReference() {
        return mPortalReference;
    }

    public void setPortalReference(DocumentReference portalReference) {
        mPortalReference = portalReference;
    }
}
