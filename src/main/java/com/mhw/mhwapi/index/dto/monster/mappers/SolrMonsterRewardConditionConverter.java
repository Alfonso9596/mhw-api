package com.mhw.mhwapi.index.dto.monster.mappers;

import com.mhw.mhwapi.domain.entities.monsterRewardCondition.MonsterRewardConditionEntity;
import com.mhw.mhwapi.index.dto.monster.SolrMonsterRewardConditionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolrMonsterRewardConditionConverter {

    @Mappings({
            @Mapping(target = "nameDe", ignore = true),
            @Mapping(target = "nameEn", ignore = true)
    })
    SolrMonsterRewardConditionDto mapToSolr(MonsterRewardConditionEntity source);
}
