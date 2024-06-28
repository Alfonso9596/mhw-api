package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.monsterHabitat.MonsterHabitatEntity;
import com.mhw.mhwapi.domain.entities.monsterHabitat.MonsterHabitatRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterHabitatDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterHabitatConverter;
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
public class MonsterHabitatIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterHabitatIndexService.class);

    public static final String COLLECTION_NAME = "monster_habitats";

    @Autowired
    private MonsterHabitatRepository monsterHabitatRepository;

    @Autowired
    private SolrMonsterHabitatConverter solrMonsterHabitatConverter;

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
            Page<MonsterHabitatEntity> monsterHabitatEntities = monsterHabitatRepository.findAll(pageable);
            totalPages = monsterHabitatEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterHabitatEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterHabitatEntity> monsterHabitatEntities) {
        if (CollectionUtils.isEmpty(monsterHabitatEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monster Habitats page {} of {}", page, totalPages);
        for (MonsterHabitatEntity monsterHabitatEntity : monsterHabitatEntities.getContent()) {
            SolrMonsterHabitatDto solrMonsterHabitatDto = solrMonsterHabitatConverter.mapToSolr(monsterHabitatEntity);

            try {
                LOGGER.info("Sending Monster Habitat {}", monsterHabitatEntity.getId());
                solrClient.addBean(solrMonsterHabitatDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

}
