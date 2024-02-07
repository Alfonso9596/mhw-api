package com.mhw.mhwapi.domain.services;

import com.mhw.mhwapi.api.v1.language.dto.LanguageDto;
import com.mhw.mhwapi.domain.entities.language.LanguageEntity;
import com.mhw.mhwapi.domain.entities.language.LanguageRepository;
import com.mhw.mhwapi.mappers.language.LanguageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageQueryService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageConverter languageConverter;

    public List<LanguageDto> getAllActiveLanguages() {
        return languageRepository.getAllActive()
                .stream()
                .map(languageConverter::map)
                .sorted(Comparator.comparing(LanguageDto::getId))
                .collect(Collectors.toList());
    }
}
