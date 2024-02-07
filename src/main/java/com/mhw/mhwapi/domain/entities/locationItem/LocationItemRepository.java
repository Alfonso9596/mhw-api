package com.mhw.mhwapi.domain.entities.locationItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationItemRepository extends JpaRepository<LocationItemEntity, Integer> {
}
