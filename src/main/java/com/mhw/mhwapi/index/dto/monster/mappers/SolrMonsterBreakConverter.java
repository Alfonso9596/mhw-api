package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterbreak.MonsterBreakEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterBreakDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterBreakConverter {

    @Mapping(target = "nameDe", ignore = true)
    @Mapping(target = "nameEn", ignore = true)
    IndexMonsterBreakDto mapToSolr(MonsterBreakEntity source);
}
