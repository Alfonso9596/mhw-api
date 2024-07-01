package com.mhw.mhwapi.domain.entities.monsterrewardcondition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRewardConditionRepository extends JpaRepository<MonsterRewardConditionEntity, Integer> {

    @Query("SELECT m FROM MonsterRewardConditionEntity m ORDER by m.id")
    List<MonsterRewardConditionEntity> getAll();
}
