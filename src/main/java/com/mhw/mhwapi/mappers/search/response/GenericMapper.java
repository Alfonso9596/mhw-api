package com.mhw.mhwapi.mappers.search.response;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.solr.common.SolrDocument;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenericMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericMapper.class);

    @AfterMapping
    public <E extends DocumentResultDto> void convertSingleDocument(@MappingTarget E result, SolrDocument document, @Context SearchResponseContext context) {
        Set<SolrFieldDefinitionDto> simpleFields = filter(context.getFields(), false);
        convertSimpleFields(result, simpleFields, document);

        Set<SolrFieldDefinitionDto> collections = filter(context.getFields(), true);
        convertSimpleCollection(result, collections, document);
    }

    private <E extends DocumentResultDto> void convertSimpleCollection(E result, Set<SolrFieldDefinitionDto> fieldDefinitions, SolrDocument document) {
        for (SolrFieldDefinitionDto fieldDefinition : fieldDefinitions) {
            String solrFieldName = fieldDefinition.getSolrFieldName();
            List<String> values = (List) document.get(solrFieldName);
            try {
                FieldUtils.writeField(result, fieldDefinition.getResponseFieldName(), values, true);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                LOGGER.warn("{}: {}", solrFieldName, e.getMessage());
            }
        }
    }

    private <E extends DocumentResultDto> void convertSimpleFields(E result, Set<SolrFieldDefinitionDto> fieldDefinitions, SolrDocument document) {
        for (SolrFieldDefinitionDto fieldDefinition : fieldDefinitions) {
            String solrFieldName = fieldDefinition.getSolrFieldName();
            Object value = document.get(solrFieldName);
            try {
                FieldUtils.writeField(result, fieldDefinition.getResponseFieldName(), value, true);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                LOGGER.warn("{}: {}", solrFieldName, e.getMessage());
            }
        }
    }

    private Set<SolrFieldDefinitionDto> filter(Set<SolrFieldDefinitionDto> fields, Boolean multivalue) {
        return fields.stream()
                .filter(field -> multivalue.equals(field.getMultiValue()))
                .collect(Collectors.toSet());
    }
}
