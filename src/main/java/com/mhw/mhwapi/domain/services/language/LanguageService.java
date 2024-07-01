package com.mhw.mhwapi.domain.services.language;

import com.mhw.mhwapi.domain.entities.language.LanguageEntity;
import com.mhw.mhwapi.domain.entities.language.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<LanguageEntity> findAllActive() {
        return languageRepository.getAllActive();
    }
}
