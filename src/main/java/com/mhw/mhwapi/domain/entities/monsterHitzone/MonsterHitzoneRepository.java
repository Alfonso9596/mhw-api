package com.mhw.mhwapi.domain.entities.monsterHitzone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterHitzoneRepository extends JpaRepository<MonsterHitzoneEntity, Integer> {
}
