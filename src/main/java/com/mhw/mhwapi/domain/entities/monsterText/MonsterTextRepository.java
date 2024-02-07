package com.mhw.mhwapi.domain.entities.monsterText;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterTextRepository extends JpaRepository<MonsterTextEntity, Integer> {

    @Query("SELECT mt FROM MonsterTextEntity mt " +
           "WHERE mt.id = :id " +
           "AND mt.langId = :lang")
    Optional<MonsterTextEntity> getMonsterTextByIdAndLang(Integer id, String lang);
}
