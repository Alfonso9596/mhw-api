package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterHabitat.MonsterHabitatEntity;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterHabitatDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterHabitatConverter {

    SolrMonsterHabitatDto mapToSolr(MonsterHabitatEntity source);
}
