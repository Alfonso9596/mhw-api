package com.mhw.mhwapi.domain.entities.solr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SolrFieldDefinitionRepository extends JpaRepository<SolrFieldDefinitionEntity, Integer> {

    Set<SolrFieldDefinitionEntity> findByCollectionId(Integer collectionId);
}
