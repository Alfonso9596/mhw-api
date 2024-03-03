package com.mhw.mhwapi.api.v1.monster.dto;

import com.mhw.mhwapi.enums.MonsterType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterSimpleDto implements Serializable {

    private Integer id;
    private Integer orderId;
    private String name;
    private String description;
    private MonsterType ecology;
    private String altStateDescription;
    private String size;
    private String icon;
    private Integer weaknessFire;
    private Integer weaknessWater;
    private Integer weaknessIce;
    private Integer weaknessThunder;
    private Integer weaknessDragon;
}
