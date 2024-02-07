package com.mhw.mhwapi.mappers.language;

import com.mhw.mhwapi.api.v1.language.dto.LanguageDto;
import com.mhw.mhwapi.domain.entities.language.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LanguageConverter {

    LanguageDto map(LanguageEntity source);

    List<LanguageDto> map(List<LanguageEntity> source);
}
