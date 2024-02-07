package com.mhw.mhwapi.api.v1.language;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.language.dto.LanguageDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.domain.services.LanguageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class LanguageQueryController extends BaseController {

    @Autowired
    private LanguageQueryService languageQueryService;

    @GetMapping(value = ApiUrlMapping.LanguageUrlMapping.GET_ALL_ACTIVE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LanguageDto>> getAllActiveLanguages() {
        return ResponseEntity.ok(languageQueryService.getAllActiveLanguages());
    }
}
