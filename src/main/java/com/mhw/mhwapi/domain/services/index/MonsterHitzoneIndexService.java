package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.monsterhitzone.MonsterHitzoneEntity;
import com.mhw.mhwapi.domain.entities.monsterhitzone.MonsterHitzoneRepository;
import com.mhw.mhwapi.domain.entities.monsterhitzonetext.MonsterHitzoneTextEntity;
import com.mhw.mhwapi.domain.entities.monsterhitzonetext.MonsterHitzoneTextRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterHitzoneDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterHitzoneConverter;
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
public class MonsterHitzoneIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterHitzoneIndexService.class);

    @Autowired
    private MonsterHitzoneRepository monsterHitzoneRepository;

    @Autowired
    private MonsterHitzoneTextRepository monsterHitzoneTextRepository;

    @Autowired
    private SolrMonsterHitzoneConverter solrMonsterHitzoneConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER_HITZONE.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterHitzoneDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterHitzoneEntity> monsterHitzoneEntities = monsterHitzoneRepository.findAll(pageable);
            totalPages = monsterHitzoneEntities.getTotalPages();

            LOGGER.info("Converting Monster Hitzones page {} of {}", page, totalPages);
            monsterHitzoneEntities.forEach(monsterHitzoneEntity -> {
                IndexMonsterHitzoneDto indexMonsterHitzoneDto = convertToIndexDto(monsterHitzoneEntity);
                LOGGER.info("Sending Monster Hitzone {}", indexMonsterHitzoneDto.getId());
                try {
                    index(solrClient, indexMonsterHitzoneDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterHitzoneDto convertToIndexDto(MonsterHitzoneEntity monsterHitzoneEntity) {
        IndexMonsterHitzoneDto indexMonsterHitzoneDto = solrMonsterHitzoneConverter.mapToSolr(monsterHitzoneEntity);
        for (String lang : LANGUAGES) {
            Optional<MonsterHitzoneTextEntity> optionalMonsterHitzoneTextEntity = monsterHitzoneTextRepository.getMonsterHitzoneTextByMonsterHitzoneIdAndLang(monsterHitzoneEntity.getId(), lang);
            MonsterHitzoneTextEntity monsterHitzoneTextEntity = optionalMonsterHitzoneTextEntity.orElse(null);
            assert monsterHitzoneTextEntity != null;

            if (monsterHitzoneTextEntity.getLangId().equals("de")) {
                indexMonsterHitzoneDto.setNameDe(monsterHitzoneTextEntity.getName());
            }
            if (monsterHitzoneTextEntity.getLangId().equals("en")) {
                indexMonsterHitzoneDto.setNameEn(monsterHitzoneTextEntity.getName());
            }
        }

        return indexMonsterHitzoneDto;
    }
}
