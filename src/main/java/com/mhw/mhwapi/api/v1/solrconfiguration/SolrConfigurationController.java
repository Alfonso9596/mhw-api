package com.mhw.mhwapi.api.v1.solrconfiguration;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.domain.services.solrconfiguration.SolrConfigurationService;
import com.mhw.mhwapi.enums.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SolrConfigurationController extends BaseController {

    @Autowired
    private SolrConfigurationService solrConfigurationService;

    @GetMapping(value = ApiUrlMapping.SolrConfigurationUrlMapping.GET_COLLECTIONS_BY_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolrCollectionDto> getSolrCollectionByType(@PathVariable(value = "type") CollectionType collectionType){
        SolrCollectionDto collectionDto = solrConfigurationService.findCollectionByType(collectionType);
        return ResponseEntity.ok(collectionDto);
    }
}
