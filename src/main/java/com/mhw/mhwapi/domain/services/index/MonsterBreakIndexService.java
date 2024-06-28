package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.monsterBreak.MonsterBreakEntity;
import com.mhw.mhwapi.domain.entities.monsterBreak.MonsterBreakRepository;
import com.mhw.mhwapi.domain.entities.monsterBreakText.MonsterBreakTextEntity;
import com.mhw.mhwapi.domain.entities.monsterBreakText.MonsterBreakTextRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterBreakDto;
import com.mhw.mhwapi.index.dto.monster.mappers.SolrMonsterBreakConverter;
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
public class MonsterBreakIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterBreakIndexService.class);

    public static final String COLLECTION_NAME = "monster_breaks";

    @Autowired
    private MonsterBreakRepository monsterBreakRepository;

    @Autowired
    private MonsterBreakTextRepository monsterBreakTextRepository;

    @Autowired
    private SolrMonsterBreakConverter solrMonsterBreakConverter;

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
            Page<MonsterBreakEntity> monsterBreakEntities = monsterBreakRepository.findAll(pageable);
            totalPages = monsterBreakEntities.getTotalPages();

            index(solrClient, page, totalPages, monsterBreakEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<MonsterBreakEntity> monsterBreakEntities) {
        if (CollectionUtils.isEmpty(monsterBreakEntities.getContent())) {
            return;
        }
        LOGGER.info("Converting Monster Breaks page {} of {}", page, totalPages);
        for (MonsterBreakEntity monsterBreakEntity : monsterBreakEntities.getContent()) {

            SolrMonsterBreakDto solrMonsterBreakDto = solrMonsterBreakConverter.mapToSolr(monsterBreakEntity);
            for (String lang : LANGUAGES) {
                Optional<MonsterBreakTextEntity> optionalMonsterBreakTextEntity = monsterBreakTextRepository.getMonsterBreakTextByMonsterBreakIdAndLang(monsterBreakEntity.getId(), lang);
                MonsterBreakTextEntity monsterBreakTextEntity = optionalMonsterBreakTextEntity.orElse(null);
                assert monsterBreakTextEntity != null;

                if (monsterBreakTextEntity.getLangId().equals("de")) {
                    solrMonsterBreakDto.setNameDe(monsterBreakTextEntity.getName());
                }
                if (monsterBreakTextEntity.getLangId().equals("en")) {
                    solrMonsterBreakDto.setNameEn(monsterBreakTextEntity.getName());
                }
            }

            try {
                LOGGER.info("Sending Monster Break {}", monsterBreakEntity.getId());
                solrClient.addBean(solrMonsterBreakDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
