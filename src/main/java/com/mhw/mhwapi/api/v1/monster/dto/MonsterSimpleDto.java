package com.mhw.mhwapi.api.v1.monster.dto;

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
    private String size;
    private String icon;
    private MonsterTextDto data;
}
