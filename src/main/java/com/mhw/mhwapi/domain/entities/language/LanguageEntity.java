package com.mhw.mhwapi.domain.entities.language;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = LanguageEntity.TABLE_NAME)
@Getter
@Setter
public class LanguageEntity {

    static final String TABLE_NAME = "language";

    @Id
    private String id;
    private String name;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;
}
