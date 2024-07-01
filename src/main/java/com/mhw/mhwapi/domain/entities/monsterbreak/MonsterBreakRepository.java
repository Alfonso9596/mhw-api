package com.mhw.mhwapi.domain.entities.monsterbreak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterBreakRepository extends JpaRepository<MonsterBreakEntity, Integer> {

    @Query("SELECT m.id FROM MonsterBreakEntity m " +
           "WHERE m.monsterId = :monsterId ORDER BY m.id")
    List<Integer> getAllIdsByMonsterId(Integer monsterId);

    @Query("SELECT m FROM MonsterBreakEntity m ORDER BY m.id")
    List<MonsterBreakEntity> getAll();
}
