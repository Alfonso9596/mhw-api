package com.mhw.mhwapi.domain.entities.monsterHabitat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterHabitatEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterHabitatEntity {

    static final String TABLE_NAME = "monster_habitat";

    @Id
    private Integer id;
    private Integer monsterId;
    private Integer locationId;
    private String startArea;
    private String moveArea;
    private String restArea;
}
