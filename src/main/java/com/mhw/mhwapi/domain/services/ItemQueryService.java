package com.mhw.mhwapi.domain.services;

import com.mhw.mhwapi.api.v1.item.dto.ItemDto;
import com.mhw.mhwapi.api.v1.item.dto.ItemSimpleDto;
import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import com.mhw.mhwapi.domain.entities.item.ItemRepository;
import com.mhw.mhwapi.domain.entities.itemCombination.ItemCombinationEntity;
import com.mhw.mhwapi.domain.entities.itemCombination.ItemCombinationRepository;
import com.mhw.mhwapi.domain.entities.itemText.ItemTextEntity;
import com.mhw.mhwapi.domain.entities.itemText.ItemTextRepository;
import com.mhw.mhwapi.mappers.item.ItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemQueryService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemTextRepository itemTextRepository;
    @Autowired
    private ItemCombinationRepository itemCombinationRepository;
    @Autowired
    private ItemConverter itemConverter;

    public List<ItemSimpleDto> getAllItems(String lang) {
        List<ItemEntity> itemEntities = itemRepository.getAllSorted();
        for (ItemEntity itemEntity : itemEntities) {
            this.addItemInformation(itemEntity, lang, true);
        }
        return itemEntities
                .stream()
                .map(itemConverter::mapSimple)
                .sorted(Comparator.comparing(ItemSimpleDto::getId))
                .collect(Collectors.toList());
    }

    public ItemDto getItemById(int id, String lang) {
        ItemEntity itemEntity = itemRepository.getReferenceById(id);
        this.addItemInformation(itemEntity, lang, false);

        return itemConverter.map(itemEntity);
    }

    private void addItemInformation(ItemEntity itemEntity, String lang, boolean isSimple) {
        Optional<ItemTextEntity> itemTextEntity = itemTextRepository.getItemTextByIdAndLang(itemEntity.getId(), lang);
        List<ItemCombinationEntity> itemCombinationEntities = itemCombinationRepository.getAllCombinationsByItem(itemEntity);

        itemTextEntity.ifPresent(itemEntity::setData);

        if (!isSimple) {
            for (ItemCombinationEntity itemCombinationEntity : itemCombinationEntities) {
                if (itemCombinationEntity.getResult().getId().equals(itemEntity.getId())) {
                    itemEntity.addItemCombinationResult(itemCombinationEntity);
                } else {
                    itemEntity.addItemCombinationRequirement(itemCombinationEntity);
                }
            }
        }
    }
}
