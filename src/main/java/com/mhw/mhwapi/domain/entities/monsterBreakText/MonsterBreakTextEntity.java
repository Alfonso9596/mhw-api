package com.mhw.mhwapi.domain.entities.monsterBreakText;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterBreakTextEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterBreakTextEntity {

    static final String TABLE_NAME = "monster_break_text";

    @Id
    private Integer id;
    private Integer monsterBreakId;
    private String langId;
    private String name;
}
