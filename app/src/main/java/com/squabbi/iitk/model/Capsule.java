package com.squabbi.iitk.model;

import java.util.LinkedList;
import java.util.List;

public class Capsule {
    private CapsuleType mCapsuleType;
    private List<Item> mItemLinkedList = new LinkedList<>();

    Capsule(CapsuleType capsuleType) {
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
