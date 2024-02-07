package com.mhw.mhwapi.domain.entities.skilltree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkilltreeRepository extends JpaRepository<SkilltreeEntity, Integer> {
}
