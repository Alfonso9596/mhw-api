package com.mhw.mhwapi.api.v1.search;

import com.google.common.collect.Sets;
import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import com.mhw.mhwapi.api.v1.search.dto.SearchResultDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.domain.services.search.SearchService;
import com.mhw.mhwapi.enums.CollectionType;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class SearchController extends BaseController {

    @Autowired
    private SearchService searchService;

    @PageableAsQueryParam
    @GetMapping(value = ApiUrlMapping.SearchUrlMapping.SEARCH_BY_TYPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResultDto<DocumentResultDto>> searchByType(
            @PathVariable(name = "lang") String lang,
            @PathVariable(name = "type") CollectionType collectionType,
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(required = false, name = "query") String query,
            @Parameter(hidden = true) Pageable pageable) {
        Map<String, Set<String>> convertedParams = convertParams(params);
        SearchResultDto<DocumentResultDto> results = searchService.search(convertedParams, query, collectionType, pageable, lang);
        return ResponseEntity.ok(results);
    }

    @GetMapping(value = ApiUrlMapping.SearchUrlMapping.SEARCH_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResultDto> searchById(
            @PathVariable(name = "lang") String lang,
            @PathVariable(name = "type") CollectionType collectionType,
            @PathVariable(name = "id") String id) {
        DocumentResultDto documentResultDto = searchService.search(id, collectionType, lang);
        return ResponseEntity.ok(documentResultDto);
    }

    private Map<String, Set<String>> convertParams(MultiValueMap<String, String> params) {
        Set<String> notAllowedFilters = Set.of("page", "size", "sort", "query");
        return params.entrySet().stream()
                .filter(param -> !notAllowedFilters.contains(param.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Sets.newHashSet(e.getValue())));
    }

}
