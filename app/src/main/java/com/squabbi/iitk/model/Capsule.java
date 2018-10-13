package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.LinkedList;
import java.util.List;

@IgnoreExtraProperties
public class Capsule extends Item {

    private CapsuleType mCapsuleType;
    private String mCapsuleId;

    // Empty constructor for Firestore
    Capsule() {}

    public Capsule(CapsuleType capsuleType, String capsuleId, Rarity rarity) {
        super(ItemType.CAPSULE, rarity, 0);
        this.mCapsuleType = capsuleType;
        this.mCapsuleId = capsuleId;
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

    public String getCapsuleId() {
        return mCapsuleId;
    }

    public void setCapsuleId(String capsuleId) {
        mCapsuleId = capsuleId;
    }
}
