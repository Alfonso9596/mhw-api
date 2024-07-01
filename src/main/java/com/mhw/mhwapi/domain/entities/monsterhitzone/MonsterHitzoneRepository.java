package com.mhw.mhwapi.domain.entities.monsterhitzone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterHitzoneRepository extends JpaRepository<MonsterHitzoneEntity, Integer> {

    @Query("SELECT m.id FROM MonsterHitzoneEntity m " +
           "WHERE m.monsterId = :monsterId ORDER BY m.id")
    List<Integer> getAllIdsByMonsterId(Integer monsterId);

    @Query("SELECT m FROM MonsterHitzoneEntity m ORDER BY m.id")
    List<MonsterHitzoneEntity> getAll();
}
