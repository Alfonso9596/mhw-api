package com.mhw.mhwapi.mappers.search.request.strategy;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;

public abstract class BaseFilterValueConverter implements FilterValueConverter {

    public String process(String result, SolrFieldDefinitionDto fieldDefinition, String value) {
        String solrFieldName = fieldDefinition.getSolrFieldName();
        if (solrFieldName == null) {
            return result;
        }
        return processValue(result, fieldDefinition);
    }

    public abstract String processValue(String value, SolrFieldDefinitionDto fieldDefinition);
}
