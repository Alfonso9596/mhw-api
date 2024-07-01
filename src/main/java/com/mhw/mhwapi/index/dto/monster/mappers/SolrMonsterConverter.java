package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterConverter {

    @Mapping(target = "nameDe", ignore = true)
    @Mapping(target = "nameEn", ignore = true)
    @Mapping(target = "ecologyDe", ignore = true)
    @Mapping(target = "ecologyEn", ignore = true)
    @Mapping(target = "descriptionDe", ignore = true)
    @Mapping(target = "descriptionEn", ignore = true)
    @Mapping(target = "primaryStateDescriptionDe", ignore = true)
    @Mapping(target = "primaryStateDescriptionEn", ignore = true)
    @Mapping(target = "secondaryStateDescriptionDe", ignore = true)
    @Mapping(target = "secondaryStateDescriptionEn", ignore = true)
    @Mapping(target = "tertiaryStateDescriptionDe", ignore = true)
    @Mapping(target = "tertiaryStateDescriptionEn", ignore = true)
    @Mapping(target = "breaks", ignore = true)
    @Mapping(target = "habitats", ignore = true)
    @Mapping(target = "hitzones", ignore = true)
    @Mapping(target = "rewards", ignore = true)
    @Mapping(target = "armorsets", ignore = true)
    IndexMonsterDto mapToSolr(MonsterEntity source);
}