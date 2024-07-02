package com.mhw.mhwapi.domain.services.solrconfiguration;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.common.EntityType;
import com.mhw.mhwapi.domain.entities.solr.SolrCollectionEntity;
import com.mhw.mhwapi.domain.entities.solr.SolrCollectionRepository;
import com.mhw.mhwapi.domain.entities.solr.SolrFieldDefinitionEntity;
import com.mhw.mhwapi.domain.entities.solr.SolrFieldDefinitionRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.exception.ItemNotFoundException;
import com.mhw.mhwapi.mappers.solrconfiguration.SolrCollectionDomainConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class SolrConfigurationService {

    @Autowired
    private SolrCollectionRepository solrCollectionRepository;

    @Autowired
    private SolrFieldDefinitionRepository solrFieldDefinitionRepository;

    @Autowired
    private SolrCollectionDomainConverter solrCollectionDomainConverter;

    public SolrCollectionDto findCollectionByType(CollectionType collectionType) {
        SolrCollectionEntity collection = solrCollectionRepository.findByCollectionType(collectionType)
                .orElseThrow(() -> ItemNotFoundException.of(EntityType.SOLR_COLLECTION, Map.of("collectionType", collectionType)));

        if (collection.getSolrFieldDefinitions().isEmpty()) {
            Set<SolrFieldDefinitionEntity> fieldDefinitions = solrFieldDefinitionRepository.findByCollectionId(collection.getId());
            collection.setSolrFieldDefinitions(fieldDefinitions);
        }

        return solrCollectionDomainConverter.map(collection);
    }

}
