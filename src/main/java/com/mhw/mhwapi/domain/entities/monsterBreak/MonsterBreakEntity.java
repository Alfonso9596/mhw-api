package com.mhw.mhwapi.domain.entities.monsterBreak;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterBreakEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterBreakEntity {

    static final String TABLE_NAME = "monster_break";

    @Id
    private Integer id;
    private Integer monsterId;
    private Integer flinch;
    private Integer wound;
    private Integer sever;
    private String extract;
}
