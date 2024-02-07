package com.mhw.mhwapi.mappers.item;

import com.mhw.mhwapi.api.v1.item.dto.ItemTextDto;
import com.mhw.mhwapi.domain.entities.itemText.ItemTextEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemTextConverter {

    ItemTextDto map(ItemTextEntity source);
}
