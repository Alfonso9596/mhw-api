package com.mhw.mhwapi.mappers.monster;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { MonsterTextConverter.class, MonsterBreakConverter.class })
public interface MonsterConverter {

    /*@Mappings({
            @Mapping(source = "source.data.name", target = "name"),
            @Mapping(source = "source.data.ecology", target = "ecology"),
            @Mapping(source = "source.data.description", target = "description"),
            @Mapping(source = "source.data.primaryStateDescription", target = "primaryStateDescription"),
            @Mapping(source = "source.data.secondaryStateDescription", target = "secondaryStateDescription"),
            @Mapping(source = "source.data.tertiaryStateDescription", target = "tertiaryStateDescription")
    })*/
    MonsterDto map(MonsterEntity source, String language);

   // List<MonsterDto> map(List<MonsterEntity> source, String language);

    /*@Mappings({
            @Mapping(source = "source.data.name", target = "name"),
            @Mapping(source = "source.data.ecology", target = "ecology"),
            @Mapping(source = "source.data.description", target = "description"),
            @Mapping(source = "source.data.primaryStateDescription", target = "primaryStateDescription"),
            @Mapping(source = "source.data.secondaryStateDescription", target = "secondaryStateDescription"),
            @Mapping(source = "source.data.tertiaryStateDescription", target = "tertiaryStateDescription")
    })*/
    MonsterSimpleDto mapSimple(MonsterEntity source, String language);

    //List<MonsterSimpleDto> mapSimple(List<MonsterEntity> source, String language);
}
