package com.mhw.mhwapi.api.v1.item.dto;

import com.mhw.mhwapi.enums.ItemCategory;
import com.mhw.mhwapi.enums.ItemSubCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class ItemSimpleDto implements Serializable {

    private Integer id;
    private ItemCategory category;
    private ItemSubCategory subcategory;
    private Integer rarity;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer points;
    private String iconName;
    private String iconColor;
    private ItemTextDto data;
}
