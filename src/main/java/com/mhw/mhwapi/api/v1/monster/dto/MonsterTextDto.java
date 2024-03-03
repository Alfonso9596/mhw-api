package com.mhw.mhwapi.api.v1.monster.dto;

import com.mhw.mhwapi.enums.MonsterType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterTextDto implements Serializable {

    private String name;
    private String description;
    private MonsterType ecology;
    private String altStateDescription;
}
