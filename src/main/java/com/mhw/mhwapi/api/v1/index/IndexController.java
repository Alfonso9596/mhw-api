package com.mhw.mhwapi.api.v1.index;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.enums.IndexType;
import com.mhw.mhwapi.index.IndexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class IndexController extends BaseController {

    @Autowired
    private IndexerService indexerService;

    @PostMapping(value = ApiUrlMapping.IndexUrlMapping.INDEX_BY_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity indexByType(
            @PathVariable(value = "type") IndexType indexType) {

        indexerService.indexByType(indexType);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
