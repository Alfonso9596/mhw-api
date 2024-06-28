package com.mhw.mhwapi.domain.entities.monsterText;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterTextRepository extends JpaRepository<MonsterTextEntity, Integer> {

    @Query("SELECT mt FROM MonsterTextEntity mt " +
           "WHERE mt.monsterId = :monsterId " +
           "AND mt.langId = :lang")
    Optional<MonsterTextEntity> getMonsterTextByMonsterIdAndLang(Integer monsterId, String lang);

    @Query("SELECT mt.monsterId FROM MonsterTextEntity mt " +
           "WHERE mt.name LIKE %:name%")
    List<Integer> getMonsterIdsByName(String name);


    @Query("SELECT mt FROM MonsterTextEntity mt " +
           " WHERE mt.id = :id")
    List<MonsterTextEntity> getAllById(Integer id);
}
