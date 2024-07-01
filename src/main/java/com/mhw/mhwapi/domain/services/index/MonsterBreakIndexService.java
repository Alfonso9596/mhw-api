package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.monsterbreak.MonsterBreakEntity;
import com.mhw.mhwapi.domain.entities.monsterbreak.MonsterBreakRepository;
import com.mhw.mhwapi.domain.entities.monsterbreaktext.MonsterBreakTextEntity;
import com.mhw.mhwapi.domain.entities.monsterbreaktext.MonsterBreakTextRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterBreakDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterBreakConverter;
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
public class MonsterBreakIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterBreakIndexService.class);

    @Autowired
    private MonsterBreakRepository monsterBreakRepository;

    @Autowired
    private MonsterBreakTextRepository monsterBreakTextRepository;

    @Autowired
    private SolrMonsterBreakConverter solrMonsterBreakConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_BREAK.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterBreakDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterBreakEntity> monsterBreakEntities = monsterBreakRepository.findAll(pageable);
            totalPages = monsterBreakEntities.getTotalPages();

            LOGGER.info("Converting Monster Breaks page {} of {}", page, totalPages);
            monsterBreakEntities.forEach(monsterBreakEntity -> {
                IndexMonsterBreakDto indexMonsterBreakDto = convertToIndexDto(monsterBreakEntity);
                LOGGER.info("Sending Monster Break {}", indexMonsterBreakDto.getId());
                try {
                    index(solrClient, indexMonsterBreakDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterBreakDto convertToIndexDto(MonsterBreakEntity monsterBreakEntity) {
        IndexMonsterBreakDto indexMonsterBreakDto = solrMonsterBreakConverter.mapToSolr(monsterBreakEntity);
        for (String lang : LANGUAGES) {
            Optional<MonsterBreakTextEntity> optionalMonsterBreakTextEntity = monsterBreakTextRepository.getMonsterBreakTextByMonsterBreakIdAndLang(monsterBreakEntity.getId(), lang);
            MonsterBreakTextEntity monsterBreakTextEntity = optionalMonsterBreakTextEntity.orElse(null);
            assert monsterBreakTextEntity != null;

            if (monsterBreakTextEntity.getLangId().equals("de")) {
                indexMonsterBreakDto.setNameDe(monsterBreakTextEntity.getName());
            }
            if (monsterBreakTextEntity.getLangId().equals("en")) {
                indexMonsterBreakDto.setNameEn(monsterBreakTextEntity.getName());
            }
        }

        return indexMonsterBreakDto;
    }
}
