package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.monsterhabitat.MonsterHabitatEntity;
import com.mhw.mhwapi.domain.entities.monsterhabitat.MonsterHabitatRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterHabitatDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterHabitatConverter;
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
public class MonsterHabitatIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterHabitatIndexService.class);

    @Autowired
    private MonsterHabitatRepository monsterHabitatRepository;

    @Autowired
    private SolrMonsterHabitatConverter solrMonsterHabitatConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_HABITAT.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterHabitatDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterHabitatEntity> monsterHabitatEntities = monsterHabitatRepository.findAll(pageable);
            totalPages = monsterHabitatEntities.getTotalPages();

            LOGGER.info("Converting Monster Habitats page {} of {}", page, totalPages);
            monsterHabitatEntities.forEach(monsterHabitatEntity ->  {
                IndexMonsterHabitatDto indexMonsterHabitatDto = convertToIndexDto(monsterHabitatEntity);
                LOGGER.info("Sending Monster Habitat {}", indexMonsterHabitatDto.getId());
                try {
                    index(solrClient, indexMonsterHabitatDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterHabitatDto convertToIndexDto(MonsterHabitatEntity monsterHabitatEntity) {
        return solrMonsterHabitatConverter.mapToSolr(monsterHabitatEntity);
    }
}
