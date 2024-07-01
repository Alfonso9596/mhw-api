package com.mhw.mhwapi.search.response;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Data
public class SearchResponseContext implements Serializable {

    private String lang;
    private Set<SolrFieldDefinitionDto> fields;
    private String id;

    public String getIdSolrName() {
        return fields.stream()
                .filter(field -> field.getResponseFieldName().equals("id"))
                .map(SolrFieldDefinitionDto::getSolrFieldName)
                .findFirst()
                .orElse(null);
    }
}
