package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterBreak.MonsterBreakEntity;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterBreakDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterBreakConverter {

    @Mappings({
            @Mapping(target = "nameDe", ignore = true),
            @Mapping(target = "nameEn", ignore = true)
    })
    SolrMonsterBreakDto mapToSolr(MonsterBreakEntity source);
}
