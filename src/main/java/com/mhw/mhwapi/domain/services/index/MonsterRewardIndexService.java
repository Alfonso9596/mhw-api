package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.monsterreward.MonsterRewardEntity;
import com.mhw.mhwapi.domain.entities.monsterreward.MonsterRewardRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterRewardDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterRewardConverter;
import org.apache.solr.client.solrj.SolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MonsterRewardIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterRewardIndexService.class);

    @Autowired
    private MonsterRewardRepository monsterRewardRepository;

    @Autowired
    private SolrMonsterRewardConverter solrMonsterRewardConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_REWARD.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterRewardDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterRewardEntity> monsterRewardEntities = monsterRewardRepository.findAll(pageable);
            totalPages = monsterRewardEntities.getTotalPages();

            LOGGER.info("Converting Monster Rewards page {} of {}", page, totalPages);
            monsterRewardEntities.forEach(monsterRewardEntity -> {
                IndexMonsterRewardDto indexMonsterRewardDto = convertToIndexDto(monsterRewardEntity);
                LOGGER.info("Sending Monster Reward {}", indexMonsterRewardDto.getId());
                try {
                    index(solrClient, indexMonsterRewardDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterRewardDto convertToIndexDto(MonsterRewardEntity monsterRewardEntity) {
        return solrMonsterRewardConverter.mapToSolr(monsterRewardEntity);
    }
}
