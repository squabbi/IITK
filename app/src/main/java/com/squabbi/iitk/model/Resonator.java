package com.squabbi.iitk.model;

/**
 * Extension class for creating Resonators, extends Item.
 */

public class Resonator extends Item {

    /**
     * Constructor for creating Resonators. Uses pre-filled super arguments to simplify
     * instantiation of Resonators.
     * @param level The level of the Resonator.
     */
    public Resonator(Integer level) {
        super(ItemType.RESONATOR, Rarity.VERY_COMMON, level);
    }
}
