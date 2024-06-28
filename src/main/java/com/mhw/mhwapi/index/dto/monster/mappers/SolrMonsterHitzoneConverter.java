package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterHitzone.MonsterHitzoneEntity;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterHitzoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterHitzoneConverter {

    @Mappings({
            @Mapping(target = "nameDe", ignore = true),
            @Mapping(target = "nameEn", ignore = true)
    })
    SolrMonsterHitzoneDto mapToSolr(MonsterHitzoneEntity source);
}
