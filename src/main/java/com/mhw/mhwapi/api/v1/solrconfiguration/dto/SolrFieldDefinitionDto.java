package com.mhw.mhwapi.api.v1.solrconfiguration.dto;

import com.mhw.mhwapi.common.SolrFieldType;
import com.mhw.mhwapi.common.WildcardType;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class SolrFieldDefinitionDto implements Serializable {

    private Integer id;
    private String importFieldName;
    private String responseFieldName;
    private Boolean multiValue;
    private SolrFieldType fieldType;
    private Boolean searchable;
    private WildcardType wildcardType;
    private Boolean freeTextSearch;
    private Double boost;
    private Map<String, String> solrFieldNames = new HashMap<>();
    private String solrFieldName;
}
