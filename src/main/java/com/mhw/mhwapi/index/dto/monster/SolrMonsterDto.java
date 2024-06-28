package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

@Data
public class SolrMonsterDto {

    @Field("id")
    Integer id;

    @Field("order_i")
    Integer orderId;

    @Field("name_de_t")
    String nameDe;

    @Field("name_en_t")
    String nameEn;

    @Field("ecology_de_t")
    String ecologyDe;

    @Field("ecology_en_t")
    String ecologyEn;

    @Field("description_de_t")
    String descriptionDe;

    @Field("description_en_t")
    String descriptionEn;

    @Field("primary_state_description_de_t")
    String primaryStateDescriptionDe;

    @Field("primary_state_description_en_t")
    String primaryStateDescriptionEn;

    @Field("secondary_state_description_de_t")
    String secondaryStateDescriptionDe;

    @Field("secondary_state_description_en_t")
    String secondaryStateDescriptionEn;

    @Field("tertiary_state_description_de_t")
    String tertiaryStateDescriptionDe;

    @Field("tertiary_state_description_en_t")
    String tertiaryStateDescriptionEn;

    @Field("size_t")
    String size;

    @Field("icon_t")
    String icon;

    @Field("image_t")
    String image;

    @Field("pitfall_trap_b")
    Boolean pitfallTrap;

    @Field("shop_trap_b")
    Boolean shockTrap;

    @Field("vine_trap_b")
    Boolean vineTrap;

    @Field("has_weakness_b")
    Boolean hasWeakness;

    @Field("has_secondary_weakness_b")
    Boolean hasSecondaryWeakness;

    @Field("has_tertiary_weakness_b")
    Boolean hasTertiaryWeakness;

    @Field("weakness_fire_i")
    Integer weaknessFire;

    @Field("weakness_water_i")
    Integer weaknessWater;

    @Field("weakness_ice_i")
    Integer weaknessIce;

    @Field("weakness_thunder_i")
    Integer weaknessThunder;

    @Field("weakness_dragon_i")
    Integer weaknessDragon;

    @Field("weakness_poison_i")
    Integer weaknessPoison;

    @Field("weakness_sleep_i")
    Integer weaknessSleep;

    @Field("weakness_paralysis_i")
    Integer weaknessParalysis;

    @Field("weakness_blast_i")
    Integer weaknessBlast;

    @Field("weakness_stun_i")
    Integer weaknessStun;

    @Field("secondary_weakness_fire_i")
    Integer secondaryWeaknessFire;

    @Field("secondary_weakness_water_i")
    Integer secondaryWeaknessWater;

    @Field("secondary_weakness_ice_i")
    Integer secondaryWeaknessIce;

    @Field("secondary_weakness_thunder_i")
    Integer secondaryWeaknessThunder;

    @Field("secondary_weakness_dragon_i")
    Integer secondaryWeaknessDragon;

    @Field("secondary_weakness_poison_i")
    Integer secondaryWeaknessPoison;

    @Field("secondary_weakness_sleep_i")
    Integer secondaryWeaknessSleep;

    @Field("secondary_weakness_paralysis_i")
    Integer secondaryWeaknessParalysis;

    @Field("secondary_weakness_blast_i")
    Integer secondaryWeaknessBlast;

    @Field("secondary_weakness_stun_i")
    Integer secondaryWeaknessStun;

    @Field("tertiary_weakness_fire_i")
    Integer tertiaryWeaknessFire;

    @Field("tertiary_weakness_water_i")
    Integer tertiaryWeaknessWater;

    @Field("tertiary_weakness_ice_i")
    Integer tertiaryWeaknessIce;

    @Field("tertiary_weakness_thunder_i")
    Integer tertiaryWeaknessThunder;

    @Field("tertiary_weakness_dragon_i")
    Integer tertiaryWeaknessDragon;

    @Field("tertiary_weakness_poison_i")
    Integer tertiaryWeaknessPoison;

    @Field("tertiary_weakness_sleep_i")
    Integer tertiaryWeaknessSleep;

    @Field("tertiary_weakness_paralysis_i")
    Integer tertiaryWeaknessParalysis;

    @Field("tertiary_weakness_blast_i")
    Integer tertiaryWeaknessBlast;

    @Field("tertiary_weakness_stun_i")
    Integer tertiaryWeaknessStun;

    @Field("ailment_roar_t")
    String ailmentRoar;

    @Field("ailment_wind_t")
    String ailmentWind;

    @Field("ailment_tremor_t")
    String ailmentTremor;

    @Field("ailment_defensedown_b")
    Boolean ailmentDefensedown;

    @Field("ailment_fireblight_b")
    Boolean ailmentFireblight;

    @Field("ailment_waterblight_b")
    Boolean ailmentWaterblight;

    @Field("ailment_iceblight_b")
    Boolean ailmentIceblight;

    @Field("ailment_thunderblight_b")
    Boolean ailmentThunderblight;

    @Field("ailment_dragonblight_b")
    Boolean ailmentDragonblight;

    @Field("ailment_blastblight_b")
    Boolean ailmentBlastblight;

    @Field("ailment_regional_b")
    Boolean ailmentRegional;

    @Field("ailment_poison_b")
    Boolean ailmentPoison;

    @Field("ailment_sleep_b")
    Boolean ailmentSleep;

    @Field("ailment_paralysis_b")
    Boolean ailmentParalysis;

    @Field("ailment_bleed_b")
    Boolean ailmentBleed;

    @Field("ailment_stun_b")
    Boolean ailmentStun;

    @Field("ailment_mud_b")
    Boolean ailmentMud;

    @Field("ailment_effluvia_b")
    Boolean ailmentEffluvia;

    @Field("breaks_ii")
    List<Integer> breaks;

    @Field("habitats_ii")
    List<Integer> habitats;

    @Field("hitzones_ii")
    List<Integer> hitzones;

    @Field("rewards_ii")
    List<Integer> rewards;

    @Field("armorsets_ii")
    List<Integer> armorsets;
}
