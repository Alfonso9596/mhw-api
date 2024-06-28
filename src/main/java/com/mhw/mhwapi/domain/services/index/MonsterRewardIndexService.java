package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.monsterReward.MonsterRewardEntity;
import com.mhw.mhwapi.domain.entities.monsterReward.MonsterRewardRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterRewardDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterRewardConverter;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
public class MonsterRewardIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterRewardIndexService.class);

    public static final String COLLECTION_NAME = "monster_rewards";

    @Autowired
    private MonsterRewardRepository monsterRewardRepository;

    @Autowired
    private SolrMonsterRewardConverter solrMonsterRewardConverter;

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public void indexByType(SolrClient solrClient) {
        int page = 0;
        int totalPages = 1;
        do {
            Pageable pageable = PageRequest.of(page, batchSize);
            Page<MonsterRewardEntity> monsterRewardEntities = monsterRewardRepository.findAll(pageable);
            totalPages = monsterRewardEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterRewardEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterRewardEntity> monsterRewardEntities) {
        if (CollectionUtils.isEmpty(monsterRewardEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monster Rewards page {} of {}", page, totalPages);
        for (MonsterRewardEntity monsterRewardEntity : monsterRewardEntities.getContent()) {
            SolrMonsterRewardDto solrMonsterRewardDto = solrMonsterRewardConverter.mapToSolr(monsterRewardEntity);

            try {
                LOGGER.info("Sending Monster Reward {}", monsterRewardEntity.getId());
                solrClient.addBean(solrMonsterRewardDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
