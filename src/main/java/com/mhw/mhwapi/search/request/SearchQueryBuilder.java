package com.mhw.mhwapi.search.request;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.enums.FieldOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class SearchQueryBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchQueryBuilder.class);

    private String query;
    private final List<SearchFieldData> filters = new ArrayList<>();
    private String lang;
    private Boolean queryPhrase;
    private Pageable pageable;

    private SearchQueryBuilder() {}

    public static SearchQueryBuilder builder() {
        return new SearchQueryBuilder();
    }

    public SearchQueryBuilder query(String query) {
        this.query = query;
        return this;
    }

    public SearchQueryBuilder andFilters(String fieldName, Set<String> values) {
        filters.add(new SearchFieldData(fieldName, values));
        return this;
    }

    public SearchQueryBuilder orFilters(String fieldName, Set<String> values) {
        filters.add(new SearchFieldData(fieldName, FieldOperator.OR, values));
        return this;
    }

    public SearchQueryBuilder lang(String lang) {
        this.lang = lang;
        return this;
    }

    public SearchQueryBuilder queryPhrase(Boolean queryPhrase) {
        this.queryPhrase = queryPhrase;
        return this;
    }

    public SearchQueryBuilder pageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public SearchQueryData build(Map<String, SolrFieldDefinitionDto> fieldDefinitions) {
        SearchQueryData result = new SearchQueryData();
        result.setQuery(this.query);
        result.setLang(this.lang);
        result.setQueryPhrase(this.queryPhrase);
        result.setPageable(pageable);

        Set<SearchFieldData> convertedFilters = new HashSet<>();

        this.filters.forEach(filter -> {
            Optional<SolrFieldDefinitionDto> fieldDefinition = find(filter.getFieldName(), fieldDefinitions);
            if (fieldDefinition.isPresent()) {
                filter.setSolrFieldName(fieldDefinition.get().getSolrFieldName());
                filter.setFieldDefinition(fieldDefinition.get());
                convertedFilters.add(filter);
            }
        });

        result.setFilters(convertedFilters);
        result.setFreeTextFields(findFreeTextFields(fieldDefinitions.values()));

        return result;
    }

    private Optional<SolrFieldDefinitionDto> find(String fieldName, Map<String, SolrFieldDefinitionDto> fieldDefinitions) {
        if (fieldDefinitions.containsKey(fieldName)) {
            return Optional.ofNullable(fieldDefinitions.get(fieldName));
        }

        LOGGER.warn("Field {} has been ignored. There is no definition for this field", fieldName);

        return Optional.empty();
    }

    private Set<SolrFieldDefinitionDto> findFreeTextFields(Collection<SolrFieldDefinitionDto> fieldDefinitionDtos) {
        return fieldDefinitionDtos.stream()
                .filter(f -> f.getFreeTextSearch() != null && f.getFreeTextSearch())
                .collect(Collectors.toSet());
    }

}
