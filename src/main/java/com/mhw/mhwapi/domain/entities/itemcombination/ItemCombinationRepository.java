package com.mhw.mhwapi.domain.entities.itemcombination;

import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCombinationRepository extends JpaRepository<ItemCombinationEntity, Integer> {

    @Query("SELECT ic FROM ItemCombinationEntity ic " +
           "WHERE ic.result = :itemEntity " +
           "OR ic.first = :itemEntity " +
           "OR ic.second = :itemEntity")
    List<ItemCombinationEntity> getAllCombinationsByItem(ItemEntity itemEntity);
}
