package com.mhw.mhwapi.domain.entities.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    @Query("SELECT l FROM LocationEntity l ORDER BY l.id")
    List<LocationEntity> getAll();
}
