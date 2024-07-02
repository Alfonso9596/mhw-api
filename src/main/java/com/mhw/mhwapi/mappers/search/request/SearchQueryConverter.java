package com.mhw.mhwapi.mappers.search.request;

import com.google.common.collect.Iterables;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.mappers.search.request.strategy.FilterValueConverter;
import com.mhw.mhwapi.search.request.SearchQueryData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchQueryConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchQueryConverter.class);

    private static final String COMMON_FIELDS = "id";
    private static final String LOCALIZED_FIELDS = "*%s*";
    private static final String SCORE = "score";
    private static final List<String> DEFAULT_FIELDS = Arrays.asList(SCORE, "*");
    private final List<FilterValueConverter> valueConverters;

    public SolrQuery convert(SearchQueryData queryData, String lang) {
        SolrQuery solrQuery = new SolrQuery();
        processFacets(solrQuery, queryData);
        processQuery(solrQuery, queryData, lang);
        processFilterQueries(solrQuery, queryData);
        processSortQuery(solrQuery, queryData);
        processLimitQuery(solrQuery, queryData);
        DEFAULT_FIELDS.forEach(solrQuery::addField);
        return solrQuery;
    }

    private void processFacets(SolrQuery solrQuery, SearchQueryData queryData) {
        if (!queryData.isFacetSearch()) {
            return;
        }
        if (queryData.getPageable() != null) {
            solrQuery.setRows(queryData.getPageable().getPageSize());
            int start = (queryData.getPageable().getPageNumber()) * queryData.getPageable().getPageSize();
            solrQuery.setStart(Math.max(start, 0));
        }
        solrQuery.setFacet(true);
        solrQuery.setFacetLimit(-1);
        solrQuery.setFacetMinCount(1);
        solrQuery.setFields(COMMON_FIELDS, String.format(LOCALIZED_FIELDS, queryData.getLang()));
        solrQuery.addFacetField(Iterables.toArray(queryData.getFacetFields(), String.class));
    }

    private void processQuery(SolrQuery solrQuery, SearchQueryData queryData, String lang) {
        if (StringUtils.isBlank(queryData.getQuery())) {
            solrQuery.setQuery("*:*");
            return;
        }
        Collection<SolrFieldDefinitionDto> freeTextFields = queryData.getFreeTextFields();
        String freeTextQuery = queryData.getQuery();
        Set<String> solrQueries = new HashSet<>();

        LOGGER.info("FREETEXTQUERY: {}", freeTextQuery);

        freeTextFields.forEach(field -> {
            String langSuffix = String.valueOf(lang.charAt(0)).toUpperCase() + lang.substring(1);
            if (Boolean.FALSE.equals(field.getLangSearch()) || (Boolean.TRUE.equals(field.getLangSearch()) && field.getResponseFieldName().endsWith(langSuffix))) {
                String value = processSingleQuery(freeTextQuery, field, queryData.isQueryPhrase(), true);
                if (value != null) {
                    solrQueries.add(field.getSolrFieldName() + ":" + value);
                }
            }
        });

        solrQuery.setQuery(String.join(" OR ", solrQueries));
    }

    private String processSingleQuery(String freeTextQuery, SolrFieldDefinitionDto fieldDefinition, Boolean phrase, Boolean freeText) {
        String result = freeTextQuery;
        for (FilterValueConverter converter : valueConverters) {
            if (converter.isSupported(fieldDefinition, phrase, freeText)) {
                result = converter.process(result, fieldDefinition, freeTextQuery);
            }
        }
        return result;
    }

    private void processFilterQueries(SolrQuery solrQuery, SearchQueryData queryData) {
        if (queryData.getFilters().isEmpty()) {
            return;
        }
        Set<String> filters = new HashSet<>();

        queryData.getFilters().forEach(filterField -> {
            String value = processQueries(filterField.getValues(), filterField.getFieldDefinition(), false, false);
            filters.add(filterField.getFieldDefinition().getSolrFieldName() + ":" + value);
        });

        solrQuery.addFilterQuery(filters.toArray(String[]::new));
    }

    private void processSortQuery(SolrQuery solrQuery, SearchQueryData queryData) {
        solrQuery.addSort(SCORE, SolrQuery.ORDER.desc);
        for (SolrFieldDefinitionDto solrFieldDefinitionDto : queryData.getSortFields().keySet()) {
            solrQuery.addSort(solrFieldDefinitionDto.getSolrFieldName(), queryData.getSortFields().get(solrFieldDefinitionDto));
        }
    }

    private void processLimitQuery(SolrQuery solrQuery, SearchQueryData queryData) {
        if (queryData.getPageable() != null) {
            solrQuery.setRows(queryData.getPageable().getPageSize());
            int start = (queryData.getPageable().getPageNumber()) * queryData.getPageable().getPageSize();
            solrQuery.setStart(Math.max(start, 0));
        }
    }

    private String processQueries(Set<String> queries, SolrFieldDefinitionDto fieldDefinition, Boolean phrase, Boolean freeText) {
        return queries.stream()
                .map(query -> processSingleQuery(query, fieldDefinition, phrase, freeText))
                .collect(Collectors.joining(" OR ", "(", ")"));
    }
}
