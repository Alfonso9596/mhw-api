package com.mhw.mhwapi.domain.entities.locationtext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationTextRepository extends JpaRepository<LocationTextEntity, Integer> {


    @Query("SELECT l FROM LocationTextEntity l " +
           "WHERE l.id = :id")
    List<LocationTextEntity> getAllById(Integer id);

    @Query("SELECT l FROM LocationTextEntity l " +
           "WHERE l.locationId = :locationId " +
           "AND l.langId = :lang")
    Optional<LocationTextEntity> getLocationTextByLocationIdAndLang(Integer locationId, String lang);
}
