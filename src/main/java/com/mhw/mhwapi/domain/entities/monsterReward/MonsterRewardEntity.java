package com.mhw.mhwapi.domain.entities.monsterReward;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterRewardEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterRewardEntity {

    static final String TABLE_NAME = "monster_reward";

    @Id
    private Integer id;
    private Integer monsterId;
    private Integer conditionId;
    private String rank;
    private Integer item;
    private Integer stack;
    private Integer percentage;
}
