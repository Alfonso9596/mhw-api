package com.mhw.mhwapi.api.v1.monster.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterHitzoneDto implements Serializable {

    private Integer cut;
    private Integer impact;
    private Integer shot;
    private Integer fire;
    private Integer water;
    private Integer ice;
    private Integer thunder;
    private Integer dragon;
    private Integer ko;
    private String name;
}
