package com.mhw.mhwapi.domain.entities.monsterRewardConditionText;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterRewardConditionTextEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterRewardConditionTextEntity {

    static final String TABLE_NAME = "monster_reward_condition_text";

    @Id
    private Integer id;
    private Integer monsterRewardConditionId;
    private String langId;
    private String name;
}
