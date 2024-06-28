package com.mhw.mhwapi.index;

import com.mhw.mhwapi.domain.services.ItemQueryService;
import com.mhw.mhwapi.domain.services.MonsterQueryService;
import com.mhw.mhwapi.domain.services.index.*;
import com.mhw.mhwapi.enums.IndexType;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IndexerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexerService.class);

    private static final String SOLR_URL = "http://localhost:8983/solr";

    @Autowired
    private MonsterQueryService monsterQueryService;

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
    private ItemQueryService itemQueryService;


    public void indexByType(IndexType indexType) {
        var queryService = this.getQueryService(indexType);
        if (queryService != null) {
            String url = SOLR_URL + "/" + queryService.getCollectionName();
            SolrClient solrClient = new Http2SolrClient.Builder(url).build();
            createCollectionIfMissing(queryService.getCollectionName());
            queryService.indexByType(solrClient);
        }
    }

    private void createCollectionIfMissing(String collection) {
        SolrClient solrClient = new Http2SolrClient.Builder(SOLR_URL).build();
        try {
            List<String> existingCollections = CollectionAdminRequest.listCollections(solrClient);

            if (!existingCollections.contains(collection)) {
                solrClient.request(CollectionAdminRequest.createCollection(collection, 1, 1));
            }
        } catch (SolrServerException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private IndexService getQueryService(IndexType indexType) {
        switch (indexType) {
            case MONSTER -> {
                return this.monsterIndexService;
            }
            case ITEM -> {
                return this.itemQueryService;
            }
            case MONSTER_HABITAT -> {
                return this.monsterHabitatIndexService;
            }
            case MONSTER_BREAK -> {
                return this.monsterBreakIndexService;
            }
            case MONSTER_HITZONE -> {
                return this.monsterHitzoneIndexService;
            }
            case MONSTER_REWARD -> {
                return this.monsterRewardIndexService;
            }
            case MONSTER_REWARD_CONDITION -> {
                return this.monsterRewardConditionIndexService;
            }
            case LOCATION -> {
                return this.locationIndexService;
            }
            case ARMOR, WEAPON -> {
                return null;
            }
        }
        return null;
    }
}
