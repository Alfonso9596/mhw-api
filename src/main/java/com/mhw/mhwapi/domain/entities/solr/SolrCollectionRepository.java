package com.mhw.mhwapi.domain.entities.solr;

import com.mhw.mhwapi.enums.CollectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolrCollectionRepository extends JpaRepository<SolrCollectionEntity, Integer> {

    Optional<SolrCollectionEntity> findByName(String name);

    Optional<SolrCollectionEntity> findByCollectionType(CollectionType collectionType);
}
