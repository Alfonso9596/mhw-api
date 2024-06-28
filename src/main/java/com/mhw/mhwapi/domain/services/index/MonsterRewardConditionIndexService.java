package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.monsterRewardCondition.MonsterRewardConditionEntity;
import com.mhw.mhwapi.domain.entities.monsterRewardCondition.MonsterRewardConditionRepository;
import com.mhw.mhwapi.domain.entities.monsterRewardConditionText.MonsterRewardConditionTextEntity;
import com.mhw.mhwapi.domain.entities.monsterRewardConditionText.MonsterRewardConditionTextRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterRewardConditionDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterRewardConditionConverter;
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
import java.util.Optional;

@Service
public class MonsterRewardConditionIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterRewardConditionIndexService.class);

    public static final String COLLECTION_NAME = "monster_reward_conditions";

    @Autowired
    private MonsterRewardConditionRepository monsterRewardConditionRepository;

    @Autowired
    private MonsterRewardConditionTextRepository monsterRewardConditionTextRepository;

    @Autowired
    private SolrMonsterRewardConditionConverter solrMonsterRewardConditionConverter;

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
            Page<MonsterRewardConditionEntity> monsterRewardConditionEntities = monsterRewardConditionRepository.findAll(pageable);
            totalPages = monsterRewardConditionEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterRewardConditionEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterRewardConditionEntity> monsterRewardConditionEntities) {
        if (CollectionUtils.isEmpty(monsterRewardConditionEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monster Reward Conditions page {} of {}", page, totalPages);
        for (MonsterRewardConditionEntity monsterRewardConditionEntity : monsterRewardConditionEntities.getContent()) {

            SolrMonsterRewardConditionDto solrMonsterRewardConditionDto = solrMonsterRewardConditionConverter.mapToSolr(monsterRewardConditionEntity);
            for (String lang : LANGUAGES) {
                Optional<MonsterRewardConditionTextEntity> optionalMonsterRewardConditionTextEntity = monsterRewardConditionTextRepository.getMonsterRewardConditionTextByMonsterRewardConditionIdAndLang(monsterRewardConditionEntity.getId(), lang);
                MonsterRewardConditionTextEntity monsterRewardConditionTextEntity = optionalMonsterRewardConditionTextEntity.orElse(null);
                assert monsterRewardConditionTextEntity != null;

                if (monsterRewardConditionTextEntity.getLangId().equals("de")) {
                    solrMonsterRewardConditionDto.setNameDe(monsterRewardConditionTextEntity.getName());
                }
                if (monsterRewardConditionTextEntity.getLangId().equals("en")) {
                    solrMonsterRewardConditionDto.setNameEn(monsterRewardConditionTextEntity.getName());
                }
            }

            try {
                LOGGER.info("Sending Monster Reward Condition {}", monsterRewardConditionEntity.getId());
                solrClient.addBean(solrMonsterRewardConditionDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
