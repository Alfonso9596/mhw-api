package com.mhw.mhwapi.mappers.item;

import com.mhw.mhwapi.api.v1.item.dto.ItemCombinationDto;
import com.mhw.mhwapi.domain.entities.itemCombination.ItemCombinationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemCombinationConverter {

    ItemCombinationDto map(ItemCombinationEntity source);
}
