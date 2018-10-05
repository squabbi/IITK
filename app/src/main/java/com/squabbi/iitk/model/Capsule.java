package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.LinkedList;
import java.util.List;

@IgnoreExtraProperties
public class Capsule extends Item {

    private CapsuleType mCapsuleType;
    private String mCapsuleId;
    // TODO: Remove lists, utilise documents/collections
    private List<Item> mCapsuleContents = new LinkedList<>();

    // Empty constructor for Firestore
    Capsule() {}

    public Capsule(CapsuleType capsuleType, String capsuleId, Rarity rarity) {
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

    public String getCapsuleId() {
        return mCapsuleId;
    }

    public void setCapsuleId(String capsuleId) {
        mCapsuleId = capsuleId;
    }

    public List<Item> getCapsuleContents() {
        return mCapsuleContents;
    }

    public void setCapsuleContents(List<Item> capsuleContents) {
        mCapsuleContents = capsuleContents;
    }

    public Integer numberOfItems() {
        return mCapsuleContents.size();
    }
}
