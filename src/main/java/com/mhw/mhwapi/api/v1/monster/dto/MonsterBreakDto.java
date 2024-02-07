package com.mhw.mhwapi.api.v1.monster.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterBreakDto implements Serializable {

    private Integer flinch;
    private Integer wound;
    private Integer sever;
    private String extract;
}
