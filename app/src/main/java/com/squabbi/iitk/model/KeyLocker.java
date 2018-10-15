package com.squabbi.iitk.model;

import com.squabbi.iitk.util.Constants;

/**
 * POJO representing the Ingress Key Locker which extends the Capsule
 */

public class KeyLocker extends Capsule {

    private Constants.LockerType mLockerType;

    // Empty constructor required by Firestore
    KeyLocker() {}

    /**
     * Constructor for creating new Key Lockers.
     * @param type Sets the colour/type of Key Locker.
     * @param capsuleId Sets ID for the Key Locker.
     */
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
