package com.mhw.mhwapi.mappers.search.request.strategy;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.common.SolrFieldType;
import com.mhw.mhwapi.common.WildcardType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FreeTextFilterValueConverter extends BaseFilterValueConverter {

    private static final String WILDCARD_CHARACTER = "*";

    @Override
    public boolean isSupported(SolrFieldDefinitionDto fieldDefinition, Boolean phrase, Boolean freeTextQuery) {
        return Boolean.TRUE.equals(freeTextQuery)
                && Boolean.TRUE.equals(fieldDefinition.getFreeTextSearch());
    }

    @Override
    public String processValue(String value, SolrFieldDefinitionDto fieldDefinition) {
        if (fieldDefinition.getWildcardType().equals(WildcardType.BOTH) ||
                fieldDefinition.getWildcardType().equals(WildcardType.LEFT)) {
            value = WILDCARD_CHARACTER + value;
        }

        if (fieldDefinition.getWildcardType().equals(WildcardType.BOTH) ||
                fieldDefinition.getWildcardType().equals(WildcardType.RIGHT)) {
            value += WILDCARD_CHARACTER;
        }
        if (fieldDefinition.getFieldType().equals(SolrFieldType.STRING)) {
            return "(" + value.toLowerCase() + " OR " + value.toUpperCase() + ")";
        }
        return value;
    }
}
