package com.mhw.mhwapi.mappers.search.response.list;

import com.mhw.mhwapi.api.v1.search.dto.RestPage;
import com.mhw.mhwapi.api.v1.search.dto.SearchResultDto;
import com.mhw.mhwapi.api.v1.search.dto.monster.MonsterResultDto;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.mappers.search.response.GenericMapper;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MonsterListResponseConverter extends GenericMapper implements ResponseListConverter {

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER.equals(collectionType);
    }

    @Override
    public SearchResultDto<MonsterResultDto> convert(SearchResponseContext context, QueryResponse response, Pageable pageable) {
        List<MonsterResultDto> monsters = convertDocuments(response.getResults(), context);
        RestPage<MonsterResultDto> page = new RestPage<>(monsters, pageable.getPageNumber(), pageable.getPageSize(), (int) response.getResults().getNumFound());
        return new SearchResultDto<>(page);
    }

    abstract List<MonsterResultDto> convertDocuments(SolrDocumentList documents, @Context SearchResponseContext context);
}
