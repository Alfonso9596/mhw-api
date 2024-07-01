package com.mhw.mhwapi.mappers.solrconfiguration;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.solr.SolrFieldDefinitionEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrFieldDefinitionDomainConverter {

    @Mapping(source = "id", target = "id")
    SolrFieldDefinitionDto map(SolrFieldDefinitionEntity source);

    @Mapping(source = "id", target = "id")
    SolrFieldDefinitionEntity map(SolrFieldDefinitionDto source);

    @AfterMapping
    default void updateSolrFieldName(@MappingTarget SolrFieldDefinitionDto solrFieldDefinitionDto) {
        String fieldCode = solrFieldDefinitionDto.getResponseFieldName();
        String multiValueSuffix = solrFieldDefinitionDto.getMultiValue() ? solrFieldDefinitionDto.getFieldType().getSuffix() : "";
        solrFieldDefinitionDto.setSolrFieldName(fieldCode + "_" + solrFieldDefinitionDto.getFieldType().getSuffix() + multiValueSuffix);
    }
}
