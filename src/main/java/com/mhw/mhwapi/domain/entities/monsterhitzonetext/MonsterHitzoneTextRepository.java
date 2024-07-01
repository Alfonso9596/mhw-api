package com.mhw.mhwapi.domain.entities.monsterhitzonetext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterHitzoneTextRepository extends JpaRepository<MonsterHitzoneTextEntity, Integer> {

    @Query("SELECT m FROM MonsterHitzoneTextEntity m " +
           "WHERE m.id = :id")
    List<MonsterHitzoneTextEntity> getAllById(Integer id);

    @Query("SELECT m FROM MonsterHitzoneTextEntity m " +
           "WHERE m.monsterHitzoneId = :monsterHitzoneId " +
           "AND m.langId = :lang")
    Optional<MonsterHitzoneTextEntity> getMonsterHitzoneTextByMonsterHitzoneIdAndLang(Integer monsterHitzoneId, String lang);
}
