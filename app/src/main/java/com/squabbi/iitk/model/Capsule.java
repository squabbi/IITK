package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.LinkedList;
import java.util.List;

@IgnoreExtraProperties
public class Capsule extends Item {

    public static final String FIELD_CAPSULE_TYPE = "capsuleType";
    public static final String FIELD_CAPSULE_ID = "capsuleId";
    //public static final String FIELD_CAPSULE_

    private CapsuleType mCapsuleType;
    private String mCapsuleId;
    private List<Item> mCapsuleContents = new LinkedList<>();

    Capsule() {}

    Capsule(CapsuleType capsuleType, String capsuleId, Rarity rarity) {
        super(rarity, 0);
        this.mCapsuleType = capsuleType;
    }

    public enum CapsuleType {
        STANDARD, QUANTUM, KEY_LOCKER
    }

    public CapsuleType getCapsuleType() {
        return mCapsuleType;
    }

    public void setCapsuleType(CapsuleType capsuleType) {
        mCapsuleType = capsuleType;
    }

    public Integer numberOfItems() {
        return mCapsuleContents.size();
    }
}
