package com.mhw.mhwapi.mappers.monster;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { MonsterTextConverter.class })
public abstract class MonsterConverter {

    public abstract MonsterDto map(MonsterEntity source);

    public abstract List<MonsterDto> map(List<MonsterEntity> source);

    public abstract MonsterSimpleDto mapSimple(MonsterEntity source);

    public abstract List<MonsterSimpleDto> mapSimple(List<MonsterEntity> source);
}
