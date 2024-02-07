package com.mhw.mhwapi.domain.entities.monster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<MonsterEntity, Integer> {

    @Query("SELECT m FROM MonsterEntity m ORDER BY m.orderId")
    List<MonsterEntity> getAll();

    @Query("SELECT m FROM MonsterEntity m WHERE m.size = :size")
    List<MonsterEntity> getBySize(String size);
}
