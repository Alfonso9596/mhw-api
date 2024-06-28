package com.mhw.mhwapi.domain.entities.monsterHabitat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterHabitatRepository extends JpaRepository<MonsterHabitatEntity, Integer> {

    @Query("SELECT m.id FROM MonsterHabitatEntity m " +
           "WHERE m.monsterId = :monsterId ORDER BY m.id")
    List<Integer> getAllIdsByMonsterId(Integer monsterId);

    @Query("SELECT m FROM MonsterHabitatEntity m ORDER BY m.id")
    List<MonsterHabitatEntity> getAll();
}
