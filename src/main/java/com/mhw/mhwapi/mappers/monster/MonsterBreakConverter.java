package com.mhw.mhwapi.mappers.monster;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterBreakDto;
import com.mhw.mhwapi.domain.entities.monsterBreak.MonsterBreakEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MonsterBreakConverter {

    /*@Mappings({
            @Mapping(source = "text.partName", target = "partName")
    })*/
    public abstract MonsterBreakDto map(MonsterBreakEntity source);

    public abstract List<MonsterBreakDto> map(List<MonsterBreakEntity> source);
}
