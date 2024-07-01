package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterhitzone.MonsterHitzoneEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterHitzoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterHitzoneConverter {

    @Mapping(target = "nameDe", ignore = true)
    @Mapping(target = "nameEn", ignore = true)
    IndexMonsterHitzoneDto mapToSolr(MonsterHitzoneEntity source);
}
