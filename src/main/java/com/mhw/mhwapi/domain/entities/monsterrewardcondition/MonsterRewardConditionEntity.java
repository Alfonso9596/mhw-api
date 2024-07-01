package com.mhw.mhwapi.domain.entities.monsterrewardcondition;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterRewardConditionEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterRewardConditionEntity {

    static final String TABLE_NAME = "monster_reward_condition";

    @Id
    private Integer id;
}
