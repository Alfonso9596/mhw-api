package com.mhw.mhwapi.domain.entities.armorset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorsetRepository extends JpaRepository<ArmorsetEntity, Integer> {
}
