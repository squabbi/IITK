package com.squabbi.iitk.model;

public class SoftBankUltraLink extends Mod {

    private boolean isSbul;

    // Empty constructor required by Firebase
    public SoftBankUltraLink() {}

    public SoftBankUltraLink(boolean isSbul) {
        super(Rarity.VERY_RARE, ModType.LINK_AMP, isSbul);
    }
}
