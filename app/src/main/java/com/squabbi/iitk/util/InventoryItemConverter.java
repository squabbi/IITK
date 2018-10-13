package com.squabbi.iitk.util;

import com.squabbi.iitk.R;
import com.squabbi.iitk.model.FirestoreItem;
import com.squabbi.iitk.model.FlipCard;
import com.squabbi.iitk.model.InventoryItem;
import com.squabbi.iitk.model.Item;
import com.squabbi.iitk.model.LawsonPowerCube;
import com.squabbi.iitk.model.Mod;
import com.squabbi.iitk.model.PowerCube;
import com.squabbi.iitk.model.Powerup;
import com.squabbi.iitk.model.Resonator;
import com.squabbi.iitk.model.SoftBankUltraLink;
import com.squabbi.iitk.model.UltraStrike;
import com.squabbi.iitk.model.XmpBurster;

import static com.squabbi.iitk.util.Constants.HeatSinkImageResources;
import static com.squabbi.iitk.util.Constants.LinkAmpImageResources;
import static com.squabbi.iitk.util.Constants.MultiHackImageResources;
import static com.squabbi.iitk.util.Constants.PowerCubeImageResources;
import static com.squabbi.iitk.util.Constants.ResonatorImageResources;
import static com.squabbi.iitk.util.Constants.ShieldImageResources;
import static com.squabbi.iitk.util.Constants.UltraStrikeImageResources;
import static com.squabbi.iitk.util.Constants.XmpImageResources;

public class InventoryItemConverter {

    public static Item determineItem(InventoryItem item) {
        // TODO: Finish capsule creation
        switch (item.getDetailItemType()) {
            case ADA:
                return new FlipCard(FlipCard.FlipType.ADA_REFACTOR);
            case JARVIS:
                return new FlipCard(FlipCard.FlipType.JARVIS_VIRUS);
            case XMP:
                return new XmpBurster(item.getLevel());
            case RESONATOR:
                return new Resonator(item.getLevel());
            case POWER_CUBE:
                return new PowerCube(item.getLevel());
            case LAWSON:
                return new LawsonPowerCube();
            case FRACKER:
                return new Powerup(Powerup.PowerupType.FRACKER);
            case BEACON:
                return new Powerup(Powerup.PowerupType.BEACON);
            case ITO_EN_M:
                return new Mod(item.getRarity(), Mod.ModType.ITO_EN_M);
            case ITO_EN_P:
                return new Mod(item.getRarity(), Mod.ModType.ITO_EN_P);
            case LINK_AMP:
                return new Mod(item.getRarity(), Mod.ModType.LINK_AMP);
            case SOFTBANK_UL:
                return new SoftBankUltraLink();
            case FORCE_AMP:
                return new Mod(item.getRarity(), Mod.ModType.FORCE_AMP);
            case HEAT_SINK:
                return new Mod(item.getRarity(), Mod.ModType.HEAT_SINK);
            case SHIELD:
                return new Mod(item.getRarity(), Mod.ModType.SHIELD);
            case AEGIS_SHIELD:
                return new Mod(item.getRarity(), Mod.ModType.SHIELD, false, true);
            case TURRET:
                return new Mod(item.getRarity(), Mod.ModType.TURRET);
            case MULTI_HACK:
                return new Mod(item.getRarity(), Mod.ModType.MULTI_HACK);
            case ULTRA_STRIKE:
                return new UltraStrike(item.getLevel());
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static InventoryItem determineInventoryItem(FirestoreItem item) {
        // Determine type of ITEM
        switch (item.getItemType()) {
            case RESONATOR:
                return new InventoryItem(Item.DetailItemType.RESONATOR,
                        R.string.resonator_level,
                        item.getRarity(),
                        item.getLevel(),
                        ResonatorImageResources[item.getLevel()-1]);
            case WEAPON:
                return constructWeapon(item);
            case MOD:
                return constructMod(item);
            case POWER_CUBE:
                return constructPowerCube(item);
            case POWERUP:
                return constructPowerup(item);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructWeapon(FirestoreItem item) {

        switch (item.getWeaponType()) {
            case BURSTER:
                return new InventoryItem(Item.DetailItemType.XMP,
                        R.string.xmp_burster_level,
                        item.getRarity(),
                        item.getLevel(),
                        XmpImageResources[item.getLevel()-1]);
            case ULTRA_STRIKE:
                return new InventoryItem(Item.DetailItemType.ULTRA_STRIKE,
                        R.string.ultra_strike_level,
                        item.getRarity(),
                        item.getLevel(),
                        UltraStrikeImageResources[item.getLevel()-1]);
            case FLIP_CARD:
                return constructFlipCard(item);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructPowerCube(FirestoreItem item) {

        if (item.isLawson()) {
            return new InventoryItem(Item.DetailItemType.POWER_CUBE,
                    R.string.power_cube_lawson,
                    item.getRarity(),
                    item.getLevel(),
                    R.drawable.power_cube_lawson);
        } else {
            return new InventoryItem(Item.DetailItemType.POWER_CUBE,
                    R.string.power_cube_level,
                    item.getRarity(),
                    item.getLevel(),
                    PowerCubeImageResources[item.getLevel()-1]);
        }
    }

    private static InventoryItem constructFlipCard(FirestoreItem item) {

        switch (item.getFlipType()) {
            case ADA_REFACTOR:
                return new InventoryItem(Item.DetailItemType.ADA,
                        R.string.flip_ada,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.flip_ada);
            case JARVIS_VIRUS:
                return new InventoryItem(Item.DetailItemType.JARVIS,
                        R.string.flip_jarvis,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.flip_jarvis);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructMod(FirestoreItem item) {

        switch (item.getModType()) {
            case ITO_EN_P:
                return new InventoryItem(Item.DetailItemType.ITO_EN_P,
                        R.string.itoem_transmuter_plus,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.itoen_transmuter_plus);
            case ITO_EN_M:
                return new InventoryItem(Item.DetailItemType.ITO_EN_M,
                        R.string.itoen_transmuter_minus,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.itoen_transmuter_minus);
            case TURRET:
                return new InventoryItem(Item.DetailItemType.TURRET,
                        R.string.turret,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.turret);
            case FORCE_AMP:
                return new InventoryItem(Item.DetailItemType.FORCE_AMP,
                        R.string.force_amp,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.force_amp);
            case MULTI_HACK:
                return constructMultiHack(item);
            case HEAT_SINK:
                return constructHeatSink(item);
            case LINK_AMP:
                return constructLinkAmp(item);
            case SHIELD:
                return constructShield(item);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructMultiHack(FirestoreItem item) {

        switch (item.getRarity()) {
            case COMMON:
                return new InventoryItem(Item.DetailItemType.MULTI_HACK,
                        R.string.multi_hack,
                        item.getRarity(),
                        item.getLevel(),
                        MultiHackImageResources[0]);
            case RARE:
                return new InventoryItem(Item.DetailItemType.MULTI_HACK,
                        R.string.multi_hack,
                        item.getRarity(),
                        item.getLevel(),
                        MultiHackImageResources[1]);
            case VERY_RARE:
                return new InventoryItem(Item.DetailItemType.MULTI_HACK,
                        R.string.multi_hack,
                        item.getRarity(),
                        item.getLevel(),
                        MultiHackImageResources[2]);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructHeatSink(FirestoreItem item) {

        switch (item.getRarity()) {
            case COMMON:
                return new InventoryItem(Item.DetailItemType.HEAT_SINK,
                        R.string.heat_sink,
                        item.getRarity(),
                        item.getLevel(),
                        HeatSinkImageResources[0]);
            case RARE:
                return new InventoryItem(Item.DetailItemType.HEAT_SINK,
                        R.string.heat_sink,
                        item.getRarity(),
                        item.getLevel(),
                        HeatSinkImageResources[1]);
            case VERY_RARE:
                return new InventoryItem(Item.DetailItemType.HEAT_SINK,
                        R.string.heat_sink,
                        item.getRarity(),
                        item.getLevel(),
                        HeatSinkImageResources[2]);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static InventoryItem constructLinkAmp(FirestoreItem item) {
        // Check if Link Amp is SoftBank UL first
        if (item.isSbul()) {
            return new InventoryItem(Item.DetailItemType.SOFTBANK_UL,
                    R.string.softbank_ultra_link,
                    item.getRarity(),
                    item.getLevel(),
                    LinkAmpImageResources[2]);
        } else {
            switch (item.getRarity()) {
                case RARE:
                    return new InventoryItem(Item.DetailItemType.LINK_AMP,
                            R.string.link_amp,
                            item.getRarity(),
                            item.getLevel(),
                            HeatSinkImageResources[0]);
                case VERY_RARE:
                    return new InventoryItem(Item.DetailItemType.LINK_AMP,
                            R.string.link_amp,
                            item.getRarity(),
                            item.getLevel(),
                            HeatSinkImageResources[1]);
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }

    private static InventoryItem constructShield(FirestoreItem item) {
        // Cast to Mod, check if it is an Aegis shield
        if (item.isAegis()) {
            return new InventoryItem(Item.DetailItemType.AEGIS_SHIELD,
                    R.string.shield_aegis,
                    item.getRarity(),
                    item.getLevel(),
                    ShieldImageResources[3]);
        } else {
            switch (item.getRarity()) {
                case COMMON:
                    return new InventoryItem(Item.DetailItemType.SHIELD,
                            R.string.shield,
                            item.getRarity(),
                            item.getLevel(),
                            ShieldImageResources[0]);
                case RARE:
                    return new InventoryItem(Item.DetailItemType.SHIELD,
                            R.string.shield,
                            item.getRarity(),
                            item.getLevel(),
                            ShieldImageResources[1]);
                case VERY_RARE:
                    return new InventoryItem(Item.DetailItemType.SHIELD,
                            R.string.shield,
                            item.getRarity(),
                            item.getLevel(),
                            ShieldImageResources[2]);
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    private static InventoryItem constructPowerup(FirestoreItem item) {
        // TODO: setup BEACON ITEM
        // Cast as Powerup
        switch (item.getPowerupType()) {
            case FRACKER:
                return new InventoryItem(Item.DetailItemType.FRACKER,
                        R.string.portal_fracker,
                        item.getRarity(),
                        item.getLevel(),
                        R.drawable.portal_fracker);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
