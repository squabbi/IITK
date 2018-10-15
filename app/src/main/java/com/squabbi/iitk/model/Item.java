package com.squabbi.iitk.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

/**
 * Super-class which represents all items within Ingress. Contains common fields and their
 * respective getter and setter methods.
 */

@IgnoreExtraProperties
public class Item {

    private ItemType mItemType;
    private Rarity mRarity;
    private Integer mLevel;

    // Empty constructor for Firestore
    Item() {}

    /**
     * Item POJO representing an Ingress item.
     * @param itemType Type of the item.
     * @param rarity Rarity of the item.
     * @param level Item level. O for non-leveled items.
     */
    Item (ItemType itemType,  Rarity rarity, Integer level) {

        this.mItemType = itemType;
        this.mRarity = rarity;
        this.mLevel = level;
    }

    /** Basic enums for different Item types, and overriden toString methods. */
    public enum ItemType {
        WEAPON {
            @Override
            public String toString() {
                return "Weapon";
            }
        }, MOD {
            @Override
            public String toString() {
                return "Mod";
            }
        }, KEY {
            @Override
            public String toString() {
                return "Portal Key";
            }
        }, KEY_LOCKER {
            @Override
            public String toString() {
                return "Key Locker";
            }
        }, CAPSULE {
            @Override
            public String toString() {
                return "Capsule";
            }
        }, POWER_CUBE {
            @Override
            public String toString() {
                return "Power Cube";
            }
        }, POWERUP {
            @Override
            public String toString() {
                return "Powerup";
            }
        }, RESONATOR {
            @Override
            public String toString() {
                return "Resonator";
            }
        }
    }

    /** More specific enums of all Items in Ingress */
    public enum DetailItemType {

        RESONATOR,
        PORTAL_KEY,
        SHIELD,
        AEGIS_SHIELD,
        FORCE_AMP,
        LINK_AMP,
        SOFTBANK_UL,
        MULTI_HACK,
        HEAT_SINK,
        TURRET,
        ITO_EN_P,
        ITO_EN_M,
        XMP,
        ULTRA_STRIKE,
        ADA,
        JARVIS,
        POWER_CUBE,
        LAWSON,
        CAPSULE,
        QUANTUM,
        LOCKER_GREEN,
        LOCKER_BLUE,
        LOCKER_WHITE,
        LOCKER_RED,
        LOCKER_YELLOW,
        LOCKER_ANNIVERSARY,
        FRACKER,
        BEACON
    }

    /** Enum of all Rarities in Ingress with overriden toString methods */
    public enum Rarity {
        VERY_COMMON {
            @Override
            public String toString() {
                return "Very Common";
            }
        }, COMMON {
            @Override
            public String toString() {
                return "Common";
            }
        }, RARE {
            @Override
            public String toString() {
                return "Rare";
            }
        }, VERY_RARE {
            @Override
            public String toString() {
                return "Very Rare";
            }
        }
    }

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
}