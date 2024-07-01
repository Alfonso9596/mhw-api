package com.mhw.mhwapi.domain.entities.monsterrewardconditiontext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterRewardConditionTextRepository extends JpaRepository<MonsterRewardConditionTextEntity, Integer> {

    @Query("SELECT m FROM MonsterRewardConditionTextEntity m " +
           "WHERE m.monsterRewardConditionId = :monsterRewardConditionId " +
           "AND m.langId = :lang")
    Optional<MonsterRewardConditionTextEntity> getMonsterRewardConditionTextByMonsterRewardConditionIdAndLang(Integer monsterRewardConditionId, String lang);
}
