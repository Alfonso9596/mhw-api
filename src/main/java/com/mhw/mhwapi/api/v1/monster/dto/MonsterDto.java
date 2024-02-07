package com.mhw.mhwapi.api.v1.monster.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterDto implements Serializable{

    private Integer id;
    private Integer orderId;
    private String size;
    private String icon;
    private Boolean pitfallTrap;
    private Boolean shockTrap;
    private Boolean vineTrap;
    private Boolean hasWeakness;
    private Boolean hasAltWeakness;
    private Integer weaknessFire;
    private Integer weaknessWater;
    private Integer weaknessIce;
    private Integer weaknessThunder;
    private Integer weaknessDragon;
    private Integer weaknessPoison;
    private Integer weaknessSleep;
    private Integer weaknessParalysis;
    private Integer weaknessBlast;
    private Integer weaknessStun;
    private Integer altWeaknessFire;
    private Integer altWeaknessWater;
    private Integer altWeaknessIce;
    private Integer altWeaknessThunder;
    private Integer altWeaknessDragon;
    private Integer altWeaknessPoison;
    private Integer altWeaknessSleep;
    private Integer altWeaknessParalysis;
    private Integer altWeaknessBlast;
    private Integer altWeaknessStun;
    private String ailmentRoar;
    private String ailmentWind;
    private String ailmentTremor;
    private Boolean ailmentDefensedown;
    private Boolean ailmentFireblight;
    private Boolean ailmentWaterblight;
    private Boolean ailmentIceblight;
    private Boolean ailmentThunderblight;
    private Boolean ailmentDragonblight;
    private Boolean ailmentBlastblight;
    private Boolean ailmentRegional;
    private Boolean ailmentPoison;
    private Boolean ailmentSleep;
    private Boolean ailmentParalysis;
    private Boolean ailmentBleed;
    private Boolean ailmentStun;
    private Boolean ailmentMud;
    private Boolean ailmentEffluvia;
    private MonsterTextDto data;
}
