package com.mhw.mhwapi.index.dto.location.mappers;

import com.mhw.mhwapi.domain.entities.location.LocationEntity;
import com.mhw.mhwapi.index.dto.location.IndexLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrLocationConverter {

    @Mapping(target = "nameDe", ignore = true)
    @Mapping(target = "nameEn", ignore = true)
    IndexLocationDto mapToSolr(LocationEntity source);
}
