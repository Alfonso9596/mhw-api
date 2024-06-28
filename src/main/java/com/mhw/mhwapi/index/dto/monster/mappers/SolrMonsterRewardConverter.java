package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterReward.MonsterRewardEntity;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterRewardDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterRewardConverter {

    SolrMonsterRewardDto mapToSolr(MonsterRewardEntity source);
}
