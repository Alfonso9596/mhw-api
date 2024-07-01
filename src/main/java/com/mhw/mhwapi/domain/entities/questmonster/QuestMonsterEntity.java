package com.mhw.mhwapi.domain.entities.questmonster;

import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.quest.QuestEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = QuestMonsterEntity.TABLE_NAME)
@Getter
@Setter
public class QuestMonsterEntity {

    static final String TABLE_NAME = "quest_monster";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = QuestEntity.class)
    @JoinColumn(name = "quest_id")
    private QuestEntity quest;
    @ManyToOne(targetEntity = MonsterEntity.class)
    @JoinColumn(name = "monster_id")
    private MonsterEntity monster;
    private Integer quantity;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean isObjective;
}
