package com.mhw.mhwapi.domain.entities.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, String> {

    @Query("SELECT l FROM LanguageEntity l " +
           "WHERE l.isActive = true")
    List<LanguageEntity> getAllActive();
}
