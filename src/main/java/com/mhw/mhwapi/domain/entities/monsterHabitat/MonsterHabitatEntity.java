package com.mhw.mhwapi.domain.entities.monsterHabitat;

import com.mhw.mhwapi.domain.entities.locationText.LocationTextEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
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
    @ManyToOne(targetEntity = MonsterEntity.class)
    @JoinColumn(name = "monster_id")
    private MonsterEntity monster;
    @ManyToOne(targetEntity = LocationTextEntity.class)
    @JoinColumn(name = "location_id")
    private LocationTextEntity location;
    private String startArea;
    private String moveArea;
    private String restArea;
}
