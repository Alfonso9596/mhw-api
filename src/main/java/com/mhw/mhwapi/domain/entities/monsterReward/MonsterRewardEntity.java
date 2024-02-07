package com.mhw.mhwapi.domain.entities.monsterReward;

import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.monsterRewardConditionText.MonsterRewardConditionTextEntity;
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
    @ManyToOne(targetEntity = MonsterEntity.class)
    @JoinColumn(name = "monster_id")
    private MonsterEntity monster;
    @ManyToOne(targetEntity = MonsterRewardConditionTextEntity.class)
    @JoinColumn(name = "condition_id")
    private MonsterRewardConditionTextEntity condition;
    @Column(name = "class")
    private String group;
    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
    private Integer stack;
    private Integer percentage;
}
