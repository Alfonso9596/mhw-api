package com.mhw.mhwapi.domain.entities.questMonster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestMonsterRepository extends JpaRepository<QuestMonsterEntity, Integer> {
}
