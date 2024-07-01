package com.mhw.mhwapi.domain.entities.monsterreward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRewardRepository extends JpaRepository<MonsterRewardEntity, Integer> {

    @Query("SELECT m.id FROM MonsterRewardEntity m " +
           "WHERE m.monsterId = :monsterId ORDER BY m.id")
    List<Integer> getAllIdsByMonsterId(Integer monsterId);

    @Query("SELECT m FROM MonsterRewardEntity m ORDER BY m.id")
    List<MonsterRewardEntity> getAll();
}
