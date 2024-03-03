package com.mhw.mhwapi.domain.entities.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterRepositoryCustom {

    @Autowired
    private MonsterRepository monsterRepository;

    public Page<MonsterEntity> findMonsterByPageable(Pageable pageable, Specification<MonsterEntity> monsterEntitySpecification) {
        return monsterRepository.findAll(monsterEntitySpecification, pageable);
    }

    public List<MonsterEntity> findMonsterBy(Specification<MonsterEntity> monsterEntitySpecification) {
        return monsterRepository.findAll(monsterEntitySpecification);
    }
}
