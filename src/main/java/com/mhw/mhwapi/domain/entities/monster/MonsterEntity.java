package com.mhw.mhwapi.domain.entities.monster;

import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterEntity {

    static final String TABLE_NAME = "monster";

    @Id
    private Integer id;
    private Integer orderId;
    private String size;
    private String icon;
    private String image;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean pitfallTrap;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean shockTrap;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean vineTrap;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean hasWeakness;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
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
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentDefensedown;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentFireblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentWaterblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentIceblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentThunderblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentDragonblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentBlastblight;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentRegional;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentPoison;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentSleep;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentParalysis;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentBleed;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentStun;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentMud;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean ailmentEffluvia;

    @Transient
    private MonsterTextEntity data;
}