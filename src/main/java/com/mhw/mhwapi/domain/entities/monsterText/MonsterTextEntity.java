package com.mhw.mhwapi.domain.entities.monsterText;

import com.mhw.mhwapi.enums.MonsterType;
import com.mhw.mhwapi.enums.converter.MonsterTypeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = MonsterTextEntity.TABLE_NAME)
@Getter
@Setter
public class MonsterTextEntity {

    static final String TABLE_NAME = "monster_text";

    @Id
    private Integer id;
    private String langId;
    private String name;
    @Convert(converter = MonsterTypeConverter.class)
    private MonsterType ecology;
    private String description;
    private String altStateDescription;
}
