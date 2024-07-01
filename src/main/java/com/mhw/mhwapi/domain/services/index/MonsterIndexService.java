package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.armorset.ArmorsetRepository;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepository;
import com.mhw.mhwapi.domain.entities.monsterbreak.MonsterBreakRepository;
import com.mhw.mhwapi.domain.entities.monsterhabitat.MonsterHabitatRepository;
import com.mhw.mhwapi.domain.entities.monsterhitzone.MonsterHitzoneRepository;
import com.mhw.mhwapi.domain.entities.monsterreward.MonsterRewardRepository;
import com.mhw.mhwapi.domain.entities.monstertext.MonsterTextEntity;
import com.mhw.mhwapi.domain.entities.monstertext.MonsterTextRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterConverter;
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
public class MonsterIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterIndexService.class);

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private MonsterTextRepository monsterTextRepository;

    @Autowired
    private MonsterBreakRepository monsterBreakRepository;

    @Autowired
    private MonsterHabitatRepository monsterHabitatRepository;

    @Autowired
    private MonsterHitzoneRepository monsterHitzoneRepository;

    @Autowired
    private MonsterRewardRepository monsterRewardRepository;

    @Autowired
    private ArmorsetRepository armorsetRepository;

    @Autowired
    private SolrMonsterConverter solrMonsterConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.MONSTER.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexMonsterDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<MonsterEntity> monsterEntities = monsterRepository.findAll(pageable);
            totalPages = monsterEntities.getTotalPages();

            LOGGER.info("Converting Monsters page {} of {}", page, totalPages);
            monsterEntities.forEach(monsterEntity -> {
                IndexMonsterDto indexMonsterDto = convertToIndexDto(monsterEntity);
                LOGGER.info("Sending Monster {}", indexMonsterDto.getId());
                try {
                    index(solrClient, indexMonsterDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexMonsterDto convertToIndexDto(MonsterEntity monsterEntity) {
        IndexMonsterDto indexMonsterDto = solrMonsterConverter.mapToSolr(monsterEntity);
        for (String lang : LANGUAGES) {
            Optional<MonsterTextEntity> optionalMonsterTextEntity = monsterTextRepository.getMonsterTextByMonsterIdAndLang(monsterEntity.getId(), lang);
            MonsterTextEntity monsterTextEntity = optionalMonsterTextEntity.orElse(null);
            assert monsterTextEntity != null;

            if (monsterTextEntity.getLangId().equals("de")) {
                indexMonsterDto.setNameDe(monsterTextEntity.getName());
                indexMonsterDto.setEcologyDe(monsterTextEntity.getEcology());
                indexMonsterDto.setDescriptionDe(monsterTextEntity.getDescription());
                indexMonsterDto.setPrimaryStateDescriptionDe(monsterTextEntity.getPrimaryStateDescription());
                indexMonsterDto.setSecondaryStateDescriptionDe(monsterTextEntity.getSecondaryStateDescription());
                indexMonsterDto.setTertiaryStateDescriptionDe(monsterTextEntity.getTertiaryStateDescription());
            }
            if (monsterTextEntity.getLangId().equals("en")){
                indexMonsterDto.setNameEn(monsterTextEntity.getName());
                indexMonsterDto.setEcologyEn(monsterTextEntity.getEcology());
                indexMonsterDto.setDescriptionEn(monsterTextEntity.getDescription());
                indexMonsterDto.setPrimaryStateDescriptionEn(monsterTextEntity.getPrimaryStateDescription());
                indexMonsterDto.setSecondaryStateDescriptionEn(monsterTextEntity.getSecondaryStateDescription());
                indexMonsterDto.setTertiaryStateDescriptionEn(monsterTextEntity.getTertiaryStateDescription());
            }
        }

        // MonsterBreakEntity mapper
        List<Integer> monsterBreakIds = monsterBreakRepository.getAllIdsByMonsterId(monsterEntity.getId());
        indexMonsterDto.setBreaks(monsterBreakIds);

        // MonsterHabitatEntity mapper
        List<Integer> monsterHabitatIds = monsterHabitatRepository.getAllIdsByMonsterId(monsterEntity.getId());
        indexMonsterDto.setHabitats(monsterHabitatIds);


        // MonsterHitzoneEntity mapper
        List<Integer> monsterHitzoneIds = monsterHitzoneRepository.getAllIdsByMonsterId(monsterEntity.getId());
        indexMonsterDto.setHitzones(monsterHitzoneIds);


        // MonsterRewardEntity mapper
        List<Integer> monsterRewardIds = monsterRewardRepository.getAllIdsByMonsterId(monsterEntity.getId());
        indexMonsterDto.setRewards(monsterRewardIds);

        List<Integer> armorsetIds = armorsetRepository.getAllIdsByMonsterId(monsterEntity.getId());
        indexMonsterDto.setArmorsets(armorsetIds);

        return indexMonsterDto;
    }
}
