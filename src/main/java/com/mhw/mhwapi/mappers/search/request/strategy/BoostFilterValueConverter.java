package com.mhw.mhwapi.mappers.search.request.strategy;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class BoostFilterValueConverter extends BaseFilterValueConverter {

    @Override
    public boolean isSupported(SolrFieldDefinitionDto fieldDefinition, Boolean phrase, Boolean freeTextQuery) {
        return Boolean.TRUE.equals(freeTextQuery) && fieldDefinition.getBoost() != null;
    }

    @Override
    public String processValue(String value, SolrFieldDefinitionDto fieldDefinition) {
        return value + "^" + fieldDefinition.getBoost();
    }
}
