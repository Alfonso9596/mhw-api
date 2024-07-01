package com.mhw.mhwapi.mappers.search.response.single;

import com.mhw.mhwapi.api.v1.search.dto.location.LocationResultDto;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.mappers.search.response.GenericMapper;
import com.mhw.mhwapi.search.response.SearchResponseContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class LocationResponseConverter extends GenericMapper implements ResponseConverter {

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.LOCATION.equals(collectionType);
    }

    @Override
    public LocationResultDto convert(SearchResponseContext context, QueryResponse response) {
        String idSolrName = context.getIdSolrName();

        Optional<LocationResultDto> location = response.getResults().stream()
                .filter(s -> s.getFieldValue(idSolrName).toString().equalsIgnoreCase(context.getId()))
                .map(doc -> convertDocument(doc, context))
                .findFirst();
        return location.orElse(null);
    }

    LocationResultDto convertDocument(SolrDocument doc, SearchResponseContext context) {
        LocationResultDto locationResultDto = new LocationResultDto();
        convertSingleDocument(locationResultDto, doc, context);
        return locationResultDto;
    }
}
