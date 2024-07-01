package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.monsterrewardcondition.MonsterRewardConditionEntity;
import com.mhw.mhwapi.domain.entities.monsterrewardcondition.MonsterRewardConditionRepository;
import com.mhw.mhwapi.domain.entities.monsterrewardconditiontext.MonsterRewardConditionTextEntity;
import com.mhw.mhwapi.domain.entities.monsterrewardconditiontext.MonsterRewardConditionTextRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterRewardConditionDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterRewardConditionConverter;
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
import java.util.Optional;

@Service
public class MonsterRewardConditionIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterRewardConditionIndexService.class);

    @Autowired
    private MonsterRewardConditionRepository monsterRewardConditionRepository;

    @Autowired
    private MonsterRewardConditionTextRepository monsterRewardConditionTextRepository;

    @Autowired
    private SolrMonsterRewardConditionConverter solrMonsterRewardConditionConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_REWARD_CONDITION.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterRewardConditionDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterRewardConditionEntity> monsterRewardConditionEntities = monsterRewardConditionRepository.findAll(pageable);
            totalPages = monsterRewardConditionEntities.getTotalPages();

            LOGGER.info("Converting Monster Reward Conditions page {} of {}", page, totalPages);
            monsterRewardConditionEntities.forEach(monsterRewardConditionEntity -> {
                IndexMonsterRewardConditionDto indexMonsterRewardConditionDto = convertToIndexDto(monsterRewardConditionEntity);
                LOGGER.info("Sending Monster Reward Condition {}", indexMonsterRewardConditionDto.getId());
                try {
                    index(solrClient, indexMonsterRewardConditionDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterRewardConditionDto convertToIndexDto(MonsterRewardConditionEntity monsterRewardConditionEntity) {
        IndexMonsterRewardConditionDto indexMonsterRewardConditionDto = solrMonsterRewardConditionConverter.mapToSolr(monsterRewardConditionEntity);
        for (String lang : LANGUAGES) {
            Optional<MonsterRewardConditionTextEntity> optionalMonsterRewardConditionTextEntity = monsterRewardConditionTextRepository.getMonsterRewardConditionTextByMonsterRewardConditionIdAndLang(monsterRewardConditionEntity.getId(), lang);
            MonsterRewardConditionTextEntity monsterRewardConditionTextEntity = optionalMonsterRewardConditionTextEntity.orElse(null);
            assert monsterRewardConditionTextEntity != null;

            if (monsterRewardConditionTextEntity.getLangId().equals("de")) {
                indexMonsterRewardConditionDto.setNameDe(monsterRewardConditionTextEntity.getName());
            }
            if (monsterRewardConditionTextEntity.getLangId().equals("en")) {
                indexMonsterRewardConditionDto.setNameEn(monsterRewardConditionTextEntity.getName());
            }
        }

        return indexMonsterRewardConditionDto;
    }
}
