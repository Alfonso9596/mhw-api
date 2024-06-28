package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.domain.entities.location.LocationEntity;
import com.mhw.mhwapi.domain.entities.location.LocationRepository;
import com.mhw.mhwapi.domain.entities.locationText.LocationTextEntity;
import com.mhw.mhwapi.domain.entities.locationText.LocationTextRepository;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.location.SolrLocationDto;
import com.mhw.mhwapi.index.dto.location.mappers.SolrLocationConverter;
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
public class LocationIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationIndexService.class);

    public static final String COLLECTION_NAME = "locations";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationTextRepository locationTextRepository;

    @Autowired
    private SolrLocationConverter solrLocationConverter;

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
            Page<LocationEntity> locationEntities = locationRepository.findAll(pageable);
            totalPages = locationEntities.getTotalPages();

            index(solrClient, page, totalPages, locationEntities);

            page++;
        } while (page <= totalPages-1);
    }

    private void index(SolrClient solrClient, int page, int totalPages, Page<LocationEntity> locationEntities) {
        if (CollectionUtils.isEmpty(locationEntities.getContent())) {
            return;
        }
        LOGGER.info("Convert Locations page {} of {}", page, totalPages);
        for (LocationEntity locationEntity : locationEntities.getContent()) {

            SolrLocationDto solrLocationDto = solrLocationConverter.mapToSolr(locationEntity);
            for (String lang : LANGUAGES) {
                Optional<LocationTextEntity> optionalLocationTextEntity = locationTextRepository.getLocationTextByLocationIdAndLang(locationEntity.getId(), lang);
                LocationTextEntity locationTextEntity = optionalLocationTextEntity.orElse(null);
                assert locationTextEntity != null;

                if (locationTextEntity.getLangId().equals("de")) {
                    solrLocationDto.setNameDe(locationTextEntity.getName());
                }
                if (locationTextEntity.getLangId().equals("en")) {
                    solrLocationDto.setNameEn(locationTextEntity.getName());
                }
            }

            try {
                LOGGER.info("Sending Location {}", locationEntity.getId());
                solrClient.addBean(solrLocationDto);
                solrClient.commit();
            } catch (IOException | SolrServerException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
