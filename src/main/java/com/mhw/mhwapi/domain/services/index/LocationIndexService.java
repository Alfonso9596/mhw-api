package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.domain.entities.location.LocationEntity;
import com.mhw.mhwapi.domain.entities.location.LocationRepository;
import com.mhw.mhwapi.domain.entities.locationtext.LocationTextEntity;
import com.mhw.mhwapi.domain.entities.locationtext.LocationTextRepository;
import com.mhw.mhwapi.enums.CollectionType;
import com.mhw.mhwapi.index.IndexService;
import com.mhw.mhwapi.index.dto.location.IndexLocationDto;
import com.mhw.mhwapi.index.dto.location.mappers.SolrLocationConverter;
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
public class LocationIndexService extends BaseIndexService implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationIndexService.class);

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationTextRepository locationTextRepository;

    @Autowired
    private SolrLocationConverter solrLocationConverter;

    @Override
    public boolean isSupported(CollectionType collectionType) {
        return CollectionType.LOCATION.equals(collectionType);
    }

    @Override
    public void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto) {
        int page = 0;
        int totalPages = 1;
        Map<Field, List<SolrFieldDefinitionDto>> configurationMap = createConfigurationMap(solrCollectionDto, IndexLocationDto.class);
        do {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<LocationEntity> locationEntities = locationRepository.findAll(pageable);
            totalPages = locationEntities.getTotalPages();

            LOGGER.info("Converting Locations page {} of {}", page, totalPages);
            locationEntities.forEach(locationEntity -> {
                IndexLocationDto indexLocationDto = convertToIndexDto(locationEntity);
                LOGGER.info("Sending Location {}", indexLocationDto.getId());
                try {
                    index(solrClient, indexLocationDto, configurationMap);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            page++;
        } while (page <= totalPages-1);
    }

    private IndexLocationDto convertToIndexDto(LocationEntity locationEntity) {
        IndexLocationDto indexLocationDto = solrLocationConverter.mapToSolr(locationEntity);
        for (String lang : LANGUAGES) {
            Optional<LocationTextEntity> optionalLocationTextEntity = locationTextRepository.getLocationTextByLocationIdAndLang(locationEntity.getId(), lang);
            LocationTextEntity locationTextEntity = optionalLocationTextEntity.orElse(null);
            assert locationTextEntity != null;

            if (locationTextEntity.getLangId().equals("de")) {
                indexLocationDto.setNameDe(locationTextEntity.getName());
            }
            if (locationTextEntity.getLangId().equals("en")) {
                indexLocationDto.setNameEn(locationTextEntity.getName());
            }
        }
        return indexLocationDto;
    }
}
