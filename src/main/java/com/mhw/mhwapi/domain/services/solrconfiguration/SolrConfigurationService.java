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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class SolrConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolrConfigurationService.class);

    @Autowired
    private SolrCollectionRepository solrCollectionRepository;

    @Autowired
    private SolrFieldDefinitionRepository solrFieldDefinitionRepository;

    @Autowired
    private SolrCollectionDomainConverter solrCollectionDomainConverter;

    public SolrCollectionDto findCollectionByType(CollectionType collectionType) {
        SolrCollectionEntity collection = solrCollectionRepository.findByCollectionType(collectionType)
                .orElseThrow(() -> ItemNotFoundException.of(EntityType.SOLR_COLLECTION, Map.of("collectionType", collectionType)));

        LOGGER.info("FIELD DEFINITION SIZE: {}", collection.getSolrFieldDefinitions().size());

        if (collection.getSolrFieldDefinitions().isEmpty()) {
            Set<SolrFieldDefinitionEntity> fieldDefinitions = solrFieldDefinitionRepository.findByCollectionId(collection.getId());
            collection.setSolrFieldDefinitions(fieldDefinitions);
        }

        LOGGER.info("COLLECTION: {}, TYPE: {}", collection.getName(), collection.getCollectionType());
        LOGGER.info("FIELD DEFINITION SIZE: {}", collection.getSolrFieldDefinitions().size());

        return solrCollectionDomainConverter.map(collection);
    }

}
