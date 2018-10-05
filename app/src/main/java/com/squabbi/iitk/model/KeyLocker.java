package com.squabbi.iitk.model;

import com.squabbi.iitk.util.Constants;

public class KeyLocker extends Capsule {

    private Constants.LockerType mLockerType;

    // Empty constructor required by Firestore
    KeyLocker() {}

    public KeyLocker(Constants.LockerType type, String capsuleId) {
        super(CapsuleType.KEY_LOCKER, capsuleId, Rarity.VERY_RARE);
        this.mLockerType = type;
    }

    public Constants.LockerType getLockerTypes() {
        return mLockerType;
    }

    public void setLockerTypes(Constants.LockerType lockerType) {
        mLockerType = lockerType;
    }
}
