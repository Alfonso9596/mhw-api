package com.mhw.mhwapi.mappers.search.response.single;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface ResponseConverter {

    boolean isSupported(CollectionType collectionType);

    <E extends DocumentResultDto> E convert(SearchResponseContext context, QueryResponse response);
}
