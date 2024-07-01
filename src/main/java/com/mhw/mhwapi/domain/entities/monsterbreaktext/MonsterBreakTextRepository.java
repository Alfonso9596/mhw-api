package com.mhw.mhwapi.domain.entities.monsterbreaktext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterBreakTextRepository extends JpaRepository<MonsterBreakTextEntity, Integer> {

    @Query("SELECT mbt FROM MonsterBreakTextEntity mbt " +
           "WHERE mbt.monsterBreakId = :monsterBreakId " +
           "AND mbt.langId = :lang")
    Optional<MonsterBreakTextEntity> getMonsterBreakTextByMonsterBreakIdAndLang(Integer monsterBreakId, String lang);

    @Query("SELECT mbt FROM MonsterBreakTextEntity mbt " +
           "WHERE mbt.id = :id")
    List<MonsterBreakTextEntity> getAllById(Integer id);
}
