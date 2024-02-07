package com.mhw.mhwapi.domain.entities.monsterHabitat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterHabitatRepository extends JpaRepository<MonsterHabitatEntity, Integer> {
}
