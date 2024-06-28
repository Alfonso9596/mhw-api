package com.mhw.mhwapi.domain.entities.monsterHitzoneText;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterHitzoneTextEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterHitzoneTextEntity {

    static final String TABLE_NAME = "monster_hitzone_text";

    @Id
    private Integer id;
    private Integer monsterHitzoneId;
    private String langId;
    private String name;

}
