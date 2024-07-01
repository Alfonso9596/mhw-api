package com.mhw.mhwapi.mappers.search.response.single;

import com.mhw.mhwapi.api.v1.search.dto.monster.MonsterHitzoneResultDto;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.mappers.search.response.GenericMapper;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MonsterHitzoneResponseConverter extends GenericMapper implements ResponseConverter {

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_HITZONE.equals(collectionType);
    }

    @Override
    public MonsterHitzoneResultDto convert(SearchResponseContext context, QueryResponse response) {
        String idSolrName = context.getIdSolrName();

        Optional<MonsterHitzoneResultDto> monsterHitzone = response.getResults().stream()
                .filter(s -> s.getFieldValue(idSolrName).toString().equals(context.getId()))
                .map(doc -> convertDocument(doc, context))
                .findFirst();
        return monsterHitzone.orElse(null);

    }

    MonsterHitzoneResultDto convertDocument(SolrDocument doc, SearchResponseContext context) {
        MonsterHitzoneResultDto monsterHitzoneResultDto = new MonsterHitzoneResultDto();
        convertSingleDocument(monsterHitzoneResultDto, doc, context);
        return monsterHitzoneResultDto;
    }
}
