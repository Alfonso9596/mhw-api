package com.mhw.mhwapi.index.dto.location.mappers;

import com.mhw.mhwapi.domain.entities.location.LocationEntity;
import com.mhw.mhwapi.index.dto.location.SolrLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrLocationConverter {

    @Mappings({
            @Mapping(target = "nameDe", ignore = true),
            @Mapping(target = "nameEn", ignore = true)
    })
    SolrLocationDto mapToSolr(LocationEntity source);
}
