package com.mhw.mhwapi.api.v1.monster;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.common.Page;
import com.mhw.mhwapi.domain.services.MonsterQueryService;
import com.mhw.mhwapi.enums.MonsterSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MonsterQueryController extends BaseController {

    private static final String DEFAULT_PAGE_SIZE = "20";
    private static final String DEFAULT_PAGE = "1";

    @Autowired
    private MonsterQueryService monsterQueryService;

    @GetMapping(value = ApiUrlMapping.MonsterUrlMapping.GET_ALL_PAGEABLE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MonsterSimpleDto>> getPageableMonsters(
            @PathVariable(value = "lang") String lang,
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "size", required = false) MonsterSize size,
            @RequestParam(value = "weaknessFire", required = false) Integer weaknessFire,
            @RequestParam(value = "weaknessWater", required = false) Integer weaknessWater,
            @RequestParam(value = "weaknessIce", required = false) Integer weaknessIce,
            @RequestParam(value = "weaknessThunder", required = false) Integer weaknessThunder,
            @RequestParam(value = "weaknessDragon", required = false) Integer weaknessDragon) {
        validateLangPath(lang);
        Map<String, Object> params = parseParams(size, weaknessFire, weaknessWater, weaknessIce, weaknessThunder, weaknessDragon);

        return ResponseEntity.ok(monsterQueryService.getPageableMonsters(lang, PageRequest.of(page - 1, pageSize), params));
    }

    @GetMapping(value = ApiUrlMapping.MonsterUrlMapping.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonsterSimpleDto>> getAllMonsters(
            @PathVariable(value = "lang") String lang,
            @RequestParam(value = "size", required = false) MonsterSize size,
            @RequestParam(value = "weaknessFire", required = false) Integer weaknessFire,
            @RequestParam(value = "weaknessWater", required = false) Integer weaknessWater,
            @RequestParam(value = "weaknessIce", required = false) Integer weaknessIce,
            @RequestParam(value = "weaknessThunder", required = false) Integer weaknessThunder,
            @RequestParam(value = "weaknessDragon", required = false) Integer weaknessDragon) {
        validateLangPath(lang);

        Map<String, Object> params = parseParams(size, weaknessFire, weaknessWater, weaknessIce, weaknessThunder, weaknessDragon);
        return ResponseEntity.ok(monsterQueryService.getAllMonsters(lang, params));
    }

    private Map<String, Object> parseParams(MonsterSize size, Integer weaknessFire, Integer weaknessWater, Integer weaknessIce, Integer weaknessThunder, Integer weaknessDragon) {
        Map<String, Object> params = new HashMap<>();
        if (size != null) {
            params.put("size", size);
        }
        if (weaknessFire != null) {
            params.put("weaknessFire", weaknessFire);
        }
        if (weaknessWater != null) {
            params.put("weaknessWater", weaknessWater);
        }
        if (weaknessIce != null) {
            params.put("weaknessIce", weaknessIce);
        }
        if (weaknessThunder != null) {
            params.put("weaknessThunder", weaknessThunder);
        }
        if (weaknessDragon != null) {
            params.put("weaknessDragon", weaknessDragon);
        }
        return params;
    }
}
