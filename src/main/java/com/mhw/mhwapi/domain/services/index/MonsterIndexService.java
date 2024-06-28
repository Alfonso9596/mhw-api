package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.armorset.ArmorsetRepository;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepository;
import com.mhw.mhwapi.domain.entities.monsterBreak.MonsterBreakRepository;
import com.mhw.mhwapi.domain.entities.monsterHabitat.MonsterHabitatRepository;
import com.mhw.mhwapi.domain.entities.monsterHitzone.MonsterHitzoneRepository;
import com.mhw.mhwapi.domain.entities.monsterReward.MonsterRewardRepository;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextEntity;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterConverter;
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
public class MonsterIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterIndexService.class);

    public static final String COLLECTION_NAME = "monsters";

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
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public void indexByType(SolrClient solrClient) {
        int page = 0;
        int totalPages = 1;
        do {
            Pageable pageable = PageRequest.of(page, batchSize);
            Page<MonsterEntity> monsterEntities = monsterRepository.findAll(pageable);
            totalPages = monsterEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterEntity> monsterEntities) {
        if (CollectionUtils.isEmpty(monsterEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monsters page {} of {}", page, totalPages);
        for (MonsterEntity monsterEntity : monsterEntities.getContent()) {

            // MonsterEntity mapper
            SolrMonsterDto solrMonsterDto = solrMonsterConverter.mapToSolr(monsterEntity);
            for (String lang : LANGUAGES) {
                Optional<MonsterTextEntity> optionalMonsterTextEntity = monsterTextRepository.getMonsterTextByMonsterIdAndLang(monsterEntity.getId(), lang);
                MonsterTextEntity monsterTextEntity = optionalMonsterTextEntity.orElse(null);
                assert monsterTextEntity != null;

                if (monsterTextEntity.getLangId().equals("de")) {
                    solrMonsterDto.setNameDe(monsterTextEntity.getName());
                    solrMonsterDto.setEcologyDe(monsterTextEntity.getEcology());
                    solrMonsterDto.setDescriptionDe(monsterTextEntity.getDescription());
                    solrMonsterDto.setPrimaryStateDescriptionDe(monsterTextEntity.getPrimaryStateDescription());
                    solrMonsterDto.setSecondaryStateDescriptionDe(monsterTextEntity.getSecondaryStateDescription());
                    solrMonsterDto.setTertiaryStateDescriptionDe(monsterTextEntity.getTertiaryStateDescription());
                }
                if (monsterTextEntity.getLangId().equals("en")){
                    solrMonsterDto.setNameEn(monsterTextEntity.getName());
                    solrMonsterDto.setEcologyEn(monsterTextEntity.getEcology());
                    solrMonsterDto.setDescriptionEn(monsterTextEntity.getDescription());
                    solrMonsterDto.setPrimaryStateDescriptionEn(monsterTextEntity.getPrimaryStateDescription());
                    solrMonsterDto.setSecondaryStateDescriptionEn(monsterTextEntity.getSecondaryStateDescription());
                    solrMonsterDto.setTertiaryStateDescriptionEn(monsterTextEntity.getTertiaryStateDescription());
                }
            }

            // MonsterBreakEntity mapper
            List<Integer> monsterBreakIds = monsterBreakRepository.getAllIdsByMonsterId(monsterEntity.getId());
            solrMonsterDto.setBreaks(monsterBreakIds);

            // MonsterHabitatEntity mapper
            List<Integer> monsterHabitatIds = monsterHabitatRepository.getAllIdsByMonsterId(monsterEntity.getId());
            solrMonsterDto.setHabitats(monsterHabitatIds);


            // MonsterHitzoneEntity mapper
            List<Integer> monsterHitzoneIds = monsterHitzoneRepository.getAllIdsByMonsterId(monsterEntity.getId());
            solrMonsterDto.setHitzones(monsterHitzoneIds);


            // MonsterRewardEntity mapper
            List<Integer> monsterRewardIds = monsterRewardRepository.getAllIdsByMonsterId(monsterEntity.getId());
            solrMonsterDto.setRewards(monsterRewardIds);

            List<Integer> armorsetIds = armorsetRepository.getAllIdsByMonsterId(monsterEntity.getId());
            solrMonsterDto.setArmorsets(armorsetIds);

            try {
                LOGGER.info("Sending Monster {}", monsterEntity.getId());
                solrClient.addBean(solrMonsterDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
