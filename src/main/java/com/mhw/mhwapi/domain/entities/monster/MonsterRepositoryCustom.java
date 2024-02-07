package com.mhw.mhwapi.domain.entities.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MonsterRepositoryCustom {

    @Autowired
    private MonsterRepository monsterRepository;

    public Page<MonsterEntity> findMonsterBy(Pageable pageable) {
        return monsterRepository.findAll(pageable);
    }
}
