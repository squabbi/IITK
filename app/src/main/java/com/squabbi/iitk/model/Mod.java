package com.squabbi.iitk.model;

/**
 * Mod POJO representing a Mod item in Ingress.
 */

public class Mod extends Item {

    /** Public enum of the various mods in Ingress */
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

    private boolean mIsSbul;
    private boolean mIsAegis;
    private ModType mModType;

    // Required public constructor
    public Mod() {}

    /**
     * Constructor for creating a Mod.
     * @param rarity Rarity of the Mod.
     * @param modType The type of Mod.
     */
    public Mod(Rarity rarity, ModType modType) {
        super(ItemType.MOD, rarity, 0);
        this.mModType = modType;
        this.mIsSbul = false;
        this.mIsAegis = false;
    }

    /**
     * Alternate constructor for SoftBank UL and Aegis shields (sponsored items).
     * @param rarity Rarity of the Mod.
     * @param modType The type of Mod.
     * @param isSbul Is the mod a SoftBank UltraLink?
     * @param isAegis Is the mod an Aegis Shield?
     */
    public Mod(Rarity rarity, ModType modType, boolean isSbul, boolean isAegis) {
        super(ItemType.MOD, rarity, 0);
        this.mModType = modType;
        this.mIsSbul = isSbul;
        this.mIsAegis = isAegis;
    }

    public ModType getModType() {
        return mModType;
    }

    public void setModType(ModType modType) {
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
}
