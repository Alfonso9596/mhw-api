package com.mhw.mhwapi.domain.entities.armorset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmorsetRepository extends JpaRepository<ArmorsetEntity, Integer> {

    @Query("SELECT a.id FROM ArmorsetEntity a " +
           "WHERE a.monster.id = :monsterId ORDER BY a.id")
    List<Integer> getAllIdsByMonsterId(Integer monsterId);
}
