package com.mhw.mhwapi.mappers.item;

import com.mhw.mhwapi.api.v1.item.dto.ItemDto;
import com.mhw.mhwapi.api.v1.item.dto.ItemSimpleDto;
import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ItemTextConverter.class, ItemCombinationConverter.class })
public abstract class ItemConverter {

    public abstract ItemDto map(ItemEntity source);

    public abstract List<ItemDto> map(List<ItemEntity> source);

    public abstract ItemSimpleDto mapSimple(ItemEntity source);

    public abstract List<ItemSimpleDto> mapSimple(List<ItemEntity> source);

    @AfterMapping
    void mapCategory(@MappingTarget ItemDto target, ItemEntity item) {
        if (item.getCategory() != null) {
            target.setCategory(item.getCategory());
        }
    }

    @AfterMapping
    void mapSubCategory(@MappingTarget ItemDto target, ItemEntity item) {
        if (item.getSubcategory() != null) {
            target.setSubcategory(item.getSubcategory());
        }
    }
}
