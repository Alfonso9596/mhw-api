package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterreward.MonsterRewardEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterRewardDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterRewardConverter {

    IndexMonsterRewardDto mapToSolr(MonsterRewardEntity source);
}
