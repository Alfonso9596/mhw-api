package com.mhw.mhwapi.api.v1.item;

import com.mhw.mhwapi.api.v1.BaseController;
import com.mhw.mhwapi.api.v1.item.dto.ItemDto;
import com.mhw.mhwapi.api.v1.item.dto.ItemSimpleDto;
import com.mhw.mhwapi.common.ApiUrlMapping;
import com.mhw.mhwapi.domain.services.ItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ItemQueryController extends BaseController {

    @Autowired
    private ItemQueryService itemQueryService;

    @GetMapping(value = ApiUrlMapping.ItemUrlMapping.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemSimpleDto>> getAllItems(
            @PathVariable(value = "lang") String lang) {
        return ResponseEntity.ok(itemQueryService.getAllItems(lang));
    }

    @GetMapping(value = ApiUrlMapping.ItemUrlMapping.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDto> getItemById(
            @PathVariable(value = "lang") String lang,
            @PathVariable(value = "id") int id) {
        return ResponseEntity.ok(itemQueryService.getItemById(id, lang));
    }
}
