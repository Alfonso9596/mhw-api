package com.mhw.mhwapi.api.v1.solrconfiguration.dto;

import com.mhw.mhwapi.enums.CollectionType;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class SolrCollectionDto implements Serializable {

    private Integer id;
    private String name;
    private CollectionType collectionType;

    private Set<SolrFieldDefinitionDto> solrFieldDefinitions = new HashSet<>();

}
