package com.mhw.mhwapi.domain.entities.armorset;

import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = ArmorsetEntity.TABLE_NAME)
@Getter
@Setter
public class ArmorsetEntity {

    static final String TABLE_NAME = "armorset";

    @Id
    private Integer id;
    @Column(name = "class")
    private String group;
    @ManyToOne(targetEntity = MonsterEntity.class)
    @JoinColumn(name = "monster_id")
    private MonsterEntity monster;
    private Integer armorsetBonusId;
}
