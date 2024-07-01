package com.mhw.mhwapi.domain.entities.monstertext;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = MonsterTextEntity.TABLE_NAME)
@Getter
@Setter
@ToString
public class MonsterTextEntity {

    static final String TABLE_NAME = "monster_text";

    @Id
    private Integer id;
    private Integer monsterId;
    private String langId;
    private String name;
    private String ecology;
    private String description;
    private String primaryStateDescription;
    private String secondaryStateDescription;
    private String tertiaryStateDescription;
}
