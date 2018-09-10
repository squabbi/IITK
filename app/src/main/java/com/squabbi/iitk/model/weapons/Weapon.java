package com.squabbi.iitk.model.weapons;

import com.squabbi.iitk.model.Item;

public class Weapon extends Item {
    private Integer mLevel;
    private Type mType;

    public enum Type {
        BURSTER, ULTRA_STRIKE, FLIP_CARD
    }

    Weapon (Type type, Integer level) {
        this.mType = type;
        this.mLevel = level;
    }

    public Integer getLevel() {
        return mLevel;
    }

    public Type getType() {
        return mType;
    }
}
