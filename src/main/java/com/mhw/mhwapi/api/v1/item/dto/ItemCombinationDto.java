package com.mhw.mhwapi.api.v1.item.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class ItemCombinationDto implements Serializable {

    private ItemSimpleDto result;
    private ItemSimpleDto first;
    private ItemSimpleDto second;
    private Integer quantity;
}
