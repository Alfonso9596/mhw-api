package com.mhw.mhwapi.domain.entities.item;

import com.mhw.mhwapi.domain.entities.itemCombination.ItemCombinationEntity;
import com.mhw.mhwapi.domain.entities.itemText.ItemTextEntity;
import com.mhw.mhwapi.enums.ItemCategory;
import com.mhw.mhwapi.enums.ItemSubCategory;
import com.mhw.mhwapi.enums.converter.ItemCategoryConverter;
import com.mhw.mhwapi.enums.converter.ItemSubCategoryConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ItemEntity.TABLE_NAME)
@Getter
@Setter
public class ItemEntity {

    static final String TABLE_NAME = "item";

    @Id
    private Integer id;
    @Convert(converter = ItemCategoryConverter.class)
    private ItemCategory category;
    @Convert(converter = ItemSubCategoryConverter.class)
    private ItemSubCategory subcategory;
    private Integer rarity;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer points;
    private String iconName;
    private String iconColor;

    @Transient
    protected ItemTextEntity data;
    @Transient
    protected List<ItemCombinationEntity> combinationResults = new ArrayList<>();
    @Transient
    protected List<ItemCombinationEntity> combinationRequirements = new ArrayList<>();

    public void addItemCombinationResult(ItemCombinationEntity itemCombination) {
        this.combinationResults.add(itemCombination);
    }

    public void removeItemCombinationResult(ItemCombinationEntity itemCombination) {
        this.combinationResults.remove(itemCombination);
    }

    public void addItemCombinationRequirement(ItemCombinationEntity itemCombination) {
        this.combinationRequirements.add(itemCombination);
    }

    public void removeItemCombinationRequirement(ItemCombinationEntity itemCombination) {
        this.combinationRequirements.remove(itemCombination);
    }
}
