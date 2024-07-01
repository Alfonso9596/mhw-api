package com.mhw.mhwapi.search.request;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.enums.FieldOperator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public class SearchFieldData {

    private final String fieldName;
    private final FieldOperator fieldOperator;
    private final Set<String> values = new HashSet<>();
    @Setter
    private String solrFieldName;
    @Setter
    private SolrFieldDefinitionDto fieldDefinition;

    public SearchFieldData(String fieldName, String value) {
        this.fieldName = fieldName;
        this.fieldOperator = FieldOperator.AND;
        this.values.add(value);
    }

    public SearchFieldData(String fieldName, Set<String> values) {
        this.fieldName = fieldName;
        this.fieldOperator = FieldOperator.AND;
        this.values.addAll(values);
    }

    public SearchFieldData(String fieldName, FieldOperator fieldOperator, Set<String> values) {
        this.fieldName = fieldName;
        this.fieldOperator = fieldOperator;
        this.values.addAll(values);
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    public void addValues(Set<String> values) {
        this.values.addAll(values);
    }
}
