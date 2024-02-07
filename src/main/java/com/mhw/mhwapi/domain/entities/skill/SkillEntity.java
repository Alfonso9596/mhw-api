package com.mhw.mhwapi.domain.entities.skill;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = SkillEntity.TABLE_NAME)
@Getter
@Setter
public class SkillEntity {

    static final String TABLE_NAME = "skill";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer skilltreeId;
    private String langId;
    private Integer level;
    private String description;
}
