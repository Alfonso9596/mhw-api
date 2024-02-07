package com.mhw.mhwapi.domain.entities.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query("SELECT i FROM ItemEntity i ORDER BY id")
    List<ItemEntity> getAllSorted();
}
