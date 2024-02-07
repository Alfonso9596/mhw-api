package com.mhw.mhwapi.domain.entities.skilltree;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = SkilltreeEntity.TABLE_NAME)
@Getter
@Setter
public class SkilltreeEntity {

    static final String TABLE_NAME = "skilltree";

    @Id
    private Integer id;
    private Integer maxLevel;
    private String iconColor;
    private Integer secret;
    private Integer unlocksId;
}
