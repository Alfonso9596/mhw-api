package com.mhw.mhwapi.domain.entities.weaponammo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface WeaponAmmoRepository extends JpaRepository<WeaponAmmoEntity, Integer> {
}
