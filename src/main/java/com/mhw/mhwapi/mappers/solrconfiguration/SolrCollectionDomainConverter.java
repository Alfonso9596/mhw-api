package com.mhw.mhwapi.mappers.solrconfiguration;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.domain.entities.solr.SolrCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = SolrFieldDefinitionDomainConverter.class)
public interface SolrCollectionDomainConverter {

    @Mapping(source = "id", target = "id")
    SolrCollectionDto map(SolrCollectionEntity source);

    @Mapping(source = "id", target = "id")
    SolrCollectionEntity map(SolrCollectionDto source);
}
