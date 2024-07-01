package com.mhw.mhwapi.domain.entities.skilltreetext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkilltreeTextRepository extends JpaRepository<SkilltreeTextEntity, Integer> {
}
