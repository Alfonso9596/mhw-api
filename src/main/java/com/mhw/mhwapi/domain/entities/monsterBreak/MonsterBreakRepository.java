package com.mhw.mhwapi.domain.entities.monsterBreak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterBreakRepository extends JpaRepository<MonsterBreakEntity, Integer> {
}
