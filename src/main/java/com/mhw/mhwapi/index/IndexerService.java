package com.mhw.mhwapi.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.domain.services.index.*;
import com.mhw.mhwapi.domain.services.solrconfiguration.SolrConfigurationService;
import com.mhw.mhwapi.enums.CollectionType;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndexerService {

    @Value("${solr.host.url}")
    private String solrUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexerService.class);

    private final List<IndexService> indexServices;

    @Autowired
    private MonsterIndexService monsterIndexService;

    @Autowired
    private MonsterHabitatIndexService monsterHabitatIndexService;

    @Autowired
    private MonsterBreakIndexService monsterBreakIndexService;

    @Autowired
    private MonsterHitzoneIndexService monsterHitzoneIndexService;

    @Autowired
    private MonsterRewardIndexService monsterRewardIndexService;

    @Autowired
    private MonsterRewardConditionIndexService monsterRewardConditionIndexService;

    @Autowired
    private LocationIndexService locationIndexService;

    @Autowired
    private SolrConfigurationService solrConfigurationService;


    public void indexByType(CollectionType collectionType) {
        var queryService = indexServices.stream()
                .filter(service -> service.isSupported(collectionType))
                .findFirst()
                .orElse(null);

        if (queryService != null) {
            SolrCollectionDto solrCollectionDto = solrConfigurationService.findCollectionByType(collectionType);
            String url = solrUrl + solrCollectionDto.getName();
            SolrClient solrClient = new Http2SolrClient.Builder(url).build();
            createCollectionIfMissing(solrCollectionDto.getName());
            queryService.indexByType(solrClient, solrCollectionDto);
        } else {
            LOGGER.error("No collection with collection type [{}] found", collectionType.name());
        }
    }

    private void createCollectionIfMissing(String collection) {
        SolrClient solrClient = new Http2SolrClient.Builder(solrUrl).build();
        try {
            List<String> existingCollections = CollectionAdminRequest.listCollections(solrClient);

            if (!existingCollections.contains(collection)) {
                solrClient.request(CollectionAdminRequest.createCollection(collection, 1, 1));
            }
        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
