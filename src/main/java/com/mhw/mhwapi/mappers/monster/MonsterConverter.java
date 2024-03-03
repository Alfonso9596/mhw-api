package com.mhw.mhwapi.mappers.monster;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { MonsterTextConverter.class })
public abstract class MonsterConverter {

    @Mappings({
            @Mapping(source = "data.name", target = "name"),
            @Mapping(source = "data.ecology", target = "ecology"),
            @Mapping(source = "data.description", target = "description"),
            @Mapping(source = "data.altStateDescription", target = "altStateDescription")
    })
    public abstract MonsterDto map(MonsterEntity source);

    public abstract List<MonsterDto> map(List<MonsterEntity> source);

    @Mappings({
            @Mapping(source = "data.name", target = "name"),
            @Mapping(source = "data.ecology", target = "ecology"),
            @Mapping(source = "data.description", target = "description"),
            @Mapping(source = "data.altStateDescription", target = "altStateDescription")
    })
    public abstract MonsterSimpleDto mapSimple(MonsterEntity source);

    public abstract List<MonsterSimpleDto> mapSimple(List<MonsterEntity> source);
}
