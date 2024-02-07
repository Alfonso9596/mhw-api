package com.mhw.mhwapi.mappers.monster;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterTextDto;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MonsterTextConverter {

    MonsterTextDto map(MonsterTextEntity source);
}
