package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterhabitat.MonsterHabitatEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterHabitatDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterHabitatConverter {

    IndexMonsterHabitatDto mapToSolr(MonsterHabitatEntity source);
}
