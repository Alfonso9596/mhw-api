package com.mhw.mhwapi.domain.entities.locationText;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTextRepository extends JpaRepository<LocationTextEntity, Integer> {
}
