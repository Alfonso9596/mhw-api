package com.mhw.mhwapi.domain.entities.monsterReward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRewardRepository extends JpaRepository<MonsterRewardEntity, Integer> {
}
