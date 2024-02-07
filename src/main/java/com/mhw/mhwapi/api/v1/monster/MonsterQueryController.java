package com.mhw.mhwapi.api.v1.monster;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.common.Page;
import com.mhw.mhwapi.domain.services.MonsterQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MonsterQueryController extends BaseController {

    private static final String DEFAULT_PAGE_SIZE = "20";
    private static final String DEFAULT_PAGE = "1";

    @Autowired
    private MonsterQueryService monsterQueryService;

    @GetMapping(value = ApiUrlMapping.MonsterUrlMapping.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MonsterDto>> getAllMonsters(
            @PathVariable(value = "lang") String lang,
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        validateLangPath(lang);

        //TimeUnit.SECONDS.sleep(5);
        return ResponseEntity.ok(monsterQueryService.getAllMonsters(lang, PageRequest.of(page - 1, pageSize)));
    }

    @GetMapping(value = ApiUrlMapping.MonsterUrlMapping.GET_BY_SIZE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonsterDto>> getMonstersBySize(
            @PathVariable(value = "lang") String lang,
            @PathVariable(value = "size") String size) {
        validateLangPath(lang);
        validateMonsterSizePath(size);

        return ResponseEntity.ok(monsterQueryService.getBySize(lang, size));
    }


}
