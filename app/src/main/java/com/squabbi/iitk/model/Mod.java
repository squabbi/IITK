package com.squabbi.iitk.model;

public class Mod extends Item {

    public enum ModType {

        SHIELD,
        FORCE_AMP,
        LINK_AMP,
        MULTI_HACK,
        HEAT_SINK,
        TURRET,
        ITO_EN_P,
        ITO_EN_M
    }

    private boolean isSbul;
    private boolean isAegis;
    private ModType mModType;

    // Required public constructor
    public Mod() {}

    public Mod(Rarity rarity, ModType modType) {
        super(ItemType.MOD, rarity, 0);
        this.mModType = modType;
        this.isSbul = false;
        this.isAegis = false;
    }

    public Mod(Rarity rarity, ModType modType, boolean isSbul, boolean isAegis) {
        super(ItemType.MOD, rarity, 0);
        this.mModType = modType;
        this.isSbul = isSbul;
        this.isAegis = isAegis;
    }

    public ModType getModType() {
        return mModType;
    }

    public void setModType(ModType modType) {
        mModType = modType;
    }

    public boolean isSbul() {
        return isSbul;
    }

    public void setSbul(boolean sbul) {
        isSbul = sbul;
    }

    public boolean isAegis() {
        return isAegis;
    }

    public void setAegis(boolean aegis) {
        isAegis = aegis;
    }
}
