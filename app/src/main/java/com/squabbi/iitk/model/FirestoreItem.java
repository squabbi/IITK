package com.squabbi.iitk.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.squabbi.iitk.util.Constants;
import com.squabbi.iitk.model.Item.ItemType;
import com.squabbi.iitk.model.Item.Rarity;

@IgnoreExtraProperties
public class FirestoreItem {

    // Super item fields
    private ItemType mItemType;
    private Rarity mRarity;
    private Integer mLevel;

    // Weapon fields
    private Weapon.WeaponType mWeaponType;

    // Mod fields
    private boolean mIsSbul;
    private boolean mIsAegis;
    private Mod.ModType mModType;

    // Capsule fields
    private Capsule.CapsuleType mCapsuleType;
    private String mCapsuleId;

    // FlipCard fields
    private FlipCard.FlipType mFlipType;

    // Key Locker fields
    private Constants.LockerType mLockerType;

    // Portal Key fields
    private String mPortalName;
    private String mPortalLocation;
    private DocumentReference mPortalReference;

    // Powercube fields
    private boolean mIsLawson;

    // Powerup fields
    private Powerup.PowerupType mPowerupType;

    // Empty constructor for Firestore
    FirestoreItem() {}

    FirestoreItem(ItemType itemType, Rarity rarity, Integer level) {

        this.mItemType = itemType;
        this.mRarity = rarity;
        this.mLevel = level;
    }

    // Stock Item getters and setters
    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        mItemType = itemType;
    }

    public Rarity getRarity() {
        return mRarity;
    }

    public void setRarity(Rarity rarity) {
        mRarity = rarity;
    }

    public Integer getLevel() {
        return mLevel;
    }

    public void setLevel(Integer level) {
        mLevel = level;
    }

    // Weapon getters/setters
    public Weapon.WeaponType getWeaponType() {
        return mWeaponType;
    }

    public void setWeaponType(Weapon.WeaponType weaponType) {
        mWeaponType = weaponType;
    }

    // Mod getters/setters
    public Mod.ModType getModType() {
        return mModType;
    }

    public void setModType(Mod.ModType modType) {
        mModType = modType;
    }

    public boolean isSbul() {
        return mIsSbul;
    }

    public void setSbul(boolean sbul) {
        mIsSbul = sbul;
    }

    public boolean isAegis() {
        return mIsAegis;
    }

    public void setAegis(boolean aegis) {
        mIsAegis = aegis;
    }

    // Capsule getters/setters
    public Capsule.CapsuleType getCapsuleType() {
        return mCapsuleType;
    }

    public void setCapsuleType(Capsule.CapsuleType capsuleType) {
        mCapsuleType = capsuleType;
    }

    public String getCapsuleId() {
        return mCapsuleId;
    }

    public void setCapsuleId(String capsuleId) {
        mCapsuleId = capsuleId;
    }

    // Flipcard setters/getters
    public FlipCard.FlipType getFlipType() {
        return mFlipType;
    }

    public void setFlipType(FlipCard.FlipType flipType) {
        mFlipType = flipType;
    }

    // Key Locker getters/setters
    public Constants.LockerType getLockerType() {
        return mLockerType;
    }

    public void setLockerType(Constants.LockerType lockerType) {
        mLockerType = lockerType;
    }

    // Portal key getters/setters
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

    // Powercube getters/setters
    public boolean isLawson() {
        return mIsLawson;
    }

    public void setLawson(boolean lawson) {
        mIsLawson = lawson;
    }

    // Powerup getters/setters
    public Powerup.PowerupType getPowerupType() {
        return mPowerupType;
    }

    public void setPowerupType(Powerup.PowerupType powerupType) {
        mPowerupType = powerupType;
    }
}