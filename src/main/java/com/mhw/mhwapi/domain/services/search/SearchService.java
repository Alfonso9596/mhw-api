package com.mhw.mhwapi.domain.services.search;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import com.mhw.mhwapi.api.v1.search.dto.SearchResultDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.services.solrconfiguration.SolrConfigurationService;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.mappers.search.request.SearchQueryConverter;
import com.mhw.mhwapi.mappers.search.response.single.ResponseConverter;
import com.mhw.mhwapi.mappers.search.response.list.ResponseListConverter;
import com.mhw.mhwapi.search.request.SearchQueryBuilder;
import com.mhw.mhwapi.search.request.SearchQueryData;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${solr.host.url}")
    private String solrUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    private static final String ID_FIELD_NAME = "id";

    private final List<ResponseListConverter> responseListConverters;

    private final List<ResponseConverter> responseConverters;

    @Autowired
    private SearchQueryConverter searchQueryConverter;

    @Autowired
    private SolrConfigurationService solrConfigurationService;

    public <E extends DocumentResultDto> E search(String id, CollectionType collectionType, String lang) {
        SolrCollectionDto collection = solrConfigurationService.findCollectionByType(collectionType);
        Map<String, SolrFieldDefinitionDto> fieldDefinitions = collection.getSolrFieldDefinitions().stream()
                .collect(Collectors.toMap(SolrFieldDefinitionDto::getResponseFieldName, Function.identity()));

        Map<String, Set<String>> filters = Map.of(ID_FIELD_NAME, Set.of(id));
        QueryResponse solrResponse = search(filters, null, lang, collection.getName(), fieldDefinitions, null);
        SearchResponseContext context = new SearchResponseContext(lang, collection.getSolrFieldDefinitions(), id);

        return (E) responseConverters.stream()
                .filter(converter -> converter.isSupported(collectionType))
                .map(converter -> converter.convert(context, solrResponse))
                .findFirst()
                .orElse(null);
    }

    public <E extends DocumentResultDto>SearchResultDto<E> search(Map<String, Set<String>> filters, String query, CollectionType collectionType, Pageable pageable, String lang) {
        SolrCollectionDto collection = solrConfigurationService.findCollectionByType(collectionType);
        Map<String, SolrFieldDefinitionDto> fieldDefinitions = collection.getSolrFieldDefinitions().stream()
                .collect(Collectors.toMap(SolrFieldDefinitionDto::getResponseFieldName, Function.identity()));

        QueryResponse solrResponse = search(filters, query, lang, collection.getName(), fieldDefinitions, pageable);
        SearchResponseContext context = new SearchResponseContext(lang, collection.getSolrFieldDefinitions(), null);

        return responseListConverters.stream()
                .filter(converter -> converter.isSupported(collectionType))
                .map(converter -> converter.convert(context, solrResponse, pageable))
                .findFirst()
                .orElse(null);
    }

    public QueryResponse search(Map<String, Set<String>> filters, String query, String lang, String collectionName, Map<String, SolrFieldDefinitionDto> fieldDefinitions, Pageable pageable) {
        SearchQueryBuilder queryBuilder = SearchQueryBuilder.builder()
                .query(query)
                .queryPhrase(true)
                .lang(lang)
                .pageable(pageable);

        MapUtils.emptyIfNull(filters).forEach(queryBuilder::andFilters);

        SearchQueryData searchQuery = queryBuilder.build(fieldDefinitions);
        searchQuery.setSortFields(getSortFieldDefinition(pageable, fieldDefinitions));
        SolrQuery solrQuery = searchQueryConverter.convert(searchQuery, lang);

        LOGGER.info("COLLECTION: {}", collectionName);
        LOGGER.info("QUERY: {}", query);
        LOGGER.info("SOLR QUERY: {}", solrQuery.toQueryString());
        LOGGER.info("SOLR QUERY DECODED: {}", URLDecoder.decode(solrQuery.toQueryString(), StandardCharsets.UTF_8));

        try {
            return getSolrClient().query(collectionName, solrQuery, SolrRequest.METHOD.POST);
        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<SolrFieldDefinitionDto, SolrQuery.ORDER> getSortFieldDefinition(Pageable pageable, Map<String, SolrFieldDefinitionDto> fieldsDefinitions) {
        Map<SolrFieldDefinitionDto, SolrQuery.ORDER> sortDefinitions = new HashMap<>();

        if (pageable == null || pageable.getSort().isUnsorted()) {
            return sortDefinitions;
        }

        Set<Sort.Order> sorts = pageable.getSort().get().collect(Collectors.toSet());
        for (Sort.Order sort : sorts) {
            SolrFieldDefinitionDto solrFieldDefinitionDto = fieldsDefinitions.get(sort.getProperty());
            if (solrFieldDefinitionDto == null) {
                continue;
            }
            sortDefinitions.put(solrFieldDefinitionDto, SolrQuery.ORDER.valueOf(sort.getDirection().name().toLowerCase()));
        }

        return sortDefinitions;
    }

    private SolrClient getSolrClient() {
        return new Http2SolrClient.Builder(solrUrl).build();
    }
}
