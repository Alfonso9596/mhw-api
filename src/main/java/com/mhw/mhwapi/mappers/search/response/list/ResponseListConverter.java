package com.mhw.mhwapi.mappers.search.response.list;

import com.mhw.mhwapi.api.v1.search.dto.SearchResultDto;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.data.domain.Pageable;

public interface ResponseListConverter {

    boolean isSupported(CollectionType collectionType);

    SearchResultDto convert(SearchResponseContext context, QueryResponse response, Pageable pageable);
}
