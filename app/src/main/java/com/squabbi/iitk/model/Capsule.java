package com.squabbi.iitk.model;

import java.util.LinkedList;
import java.util.List;

public class Capsule extends Item {
    private CapsuleType mCapsuleType;
    private String mCapsuleId;
    private List<Item> mItemLinkedList = new LinkedList<>();

    Capsule(CapsuleType capsuleType, String capsuleId, Rarity rarity) {
        super(rarity);
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
        return mItemLinkedList.size();
    }
}
