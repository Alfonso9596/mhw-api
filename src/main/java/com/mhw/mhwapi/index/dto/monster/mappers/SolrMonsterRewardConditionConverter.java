package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterrewardcondition.MonsterRewardConditionEntity;
import com.mhw.mhwapi.index.dto.monster.IndexMonsterRewardConditionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterRewardConditionConverter {

    @Mapping(target = "nameDe", ignore = true)
    @Mapping(target = "nameEn", ignore = true)
    IndexMonsterRewardConditionDto mapToSolr(MonsterRewardConditionEntity source);
}
