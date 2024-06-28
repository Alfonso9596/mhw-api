package com.mhw.mhwapi.domain.entities.monsterHitzone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterHitzoneEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterHitzoneEntity {

    static final String TABLE_NAME = "monster_hitzone";

    @Id
    private Integer id;
    private Integer monsterId;
    private Integer cut;
    private Integer impact;
    private Integer shot;
    private Integer fire;
    private Integer water;
    private Integer ice;
    private Integer thunder;
    private Integer dragon;
    private Integer ko;
}
