package com.mhw.mhwapi.search.request;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.enums.SearchType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode
public class SearchQueryData implements Serializable {

    private SearchType searchType;
    private String query;
    private boolean queryPhrase;
    private String lang;
    private Set<SearchFieldData> filters = new HashSet<>();
    private Set<SolrFieldDefinitionDto> freeTextFields = new HashSet<>();
    private Map<SolrFieldDefinitionDto, SolrQuery.ORDER> sortFields = new HashMap<>();
    private Set<String> facetFields = new HashSet<>();
    private boolean facetSearch;
    private Pageable pageable;
}
