package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.monsterHitzone.MonsterHitzoneEntity;
import com.mhw.mhwapi.domain.entities.monsterHitzone.MonsterHitzoneRepository;
import com.mhw.mhwapi.domain.entities.monsterHitzoneText.MonsterHitzoneTextEntity;
import com.mhw.mhwapi.domain.entities.monsterHitzoneText.MonsterHitzoneTextRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterHitzoneDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterHitzoneConverter;
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
public class MonsterHitzoneIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterHitzoneIndexService.class);

    public static final String COLLECTION_NAME = "monster_hitzones";

    @Autowired
    private MonsterHitzoneRepository monsterHitzoneRepository;

    @Autowired
    private MonsterHitzoneTextRepository monsterHitzoneTextRepository;

    @Autowired
    private SolrMonsterHitzoneConverter solrMonsterHitzoneConverter;

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
            Page<MonsterHitzoneEntity> monsterHitzoneEntities = monsterHitzoneRepository.findAll(pageable);
            totalPages = monsterHitzoneEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterHitzoneEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterHitzoneEntity> monsterHitzoneEntities) {
        if (CollectionUtils.isEmpty(monsterHitzoneEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monster Hitzones page {} of {}", page, totalPages);
        for (MonsterHitzoneEntity monsterHitzoneEntity : monsterHitzoneEntities.getContent()) {

            SolrMonsterHitzoneDto solrMonsterHitzoneDto = solrMonsterHitzoneConverter.mapToSolr(monsterHitzoneEntity);
            for (String lang : LANGUAGES) {
                Optional<MonsterHitzoneTextEntity> optionalMonsterHitzoneTextEntity = monsterHitzoneTextRepository.getMonsterHitzoneTextByMonsterHitzoneIdAndLang(monsterHitzoneEntity.getId(), lang);
                MonsterHitzoneTextEntity monsterHitzoneTextEntity = optionalMonsterHitzoneTextEntity.orElse(null);
                assert monsterHitzoneTextEntity != null;

                if (monsterHitzoneTextEntity.getLangId().equals("de")) {
                    solrMonsterHitzoneDto.setNameDe(monsterHitzoneTextEntity.getName());
                }
                if (monsterHitzoneTextEntity.getLangId().equals("en")) {
                    solrMonsterHitzoneDto.setNameEn(monsterHitzoneTextEntity.getName());
                }
            }

            try {
                LOGGER.info("Sending Monster Hitzone {}", monsterHitzoneEntity.getId());
                solrClient.addBean(solrMonsterHitzoneDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
