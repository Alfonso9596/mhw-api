package com.mhw.mhwapi.mappers.search.request.strategy;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;

public interface FilterValueConverter {

    boolean isSupported(SolrFieldDefinitionDto fieldDefinition, Boolean phrase, Boolean freeTextQuery);

    String process(String result, SolrFieldDefinitionDto fieldDefinition, String value);
}
