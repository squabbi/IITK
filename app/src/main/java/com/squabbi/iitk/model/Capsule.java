package com.squabbi.iitk.model;

public class Capsule {
    private CapsuleType mCapsuleType;

    Capsule(CapsuleType capsuleType) {
        this.mCapsuleType = capsuleType;
    }

    public enum CapsuleType {
        STANDARD, QUANTUM, KEY_LOCKER
    }

    public CapsuleType getCapsuleType() {
        return mCapsuleType;
    }
}
