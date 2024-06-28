package com.mhw.mhwapi.domain.entities.itemText;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemTextRepository extends JpaRepository<ItemTextEntity, Integer> {

    @Query("SELECT it FROM ItemTextEntity it " +
           "WHERE it.id = :id " +
           "AND it.langId = :lang")
    Optional<ItemTextEntity> getItemTextByIdAndLang(Integer id, String lang);


    @Query("SELECT it FROM ItemTextEntity it " +
           "WHERE it.id = :id")
    List<ItemTextEntity> getAllById(Integer id);

}
