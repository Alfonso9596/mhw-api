package com.mhw.mhwapi.domain.entities.skilltreeText;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = SkilltreeTextEntity.TABLE_NAME)
@Getter
@Setter
public class SkilltreeTextEntity {

    static final String TABLE_NAME = "skilltree_text";

    @Id
    private Integer id;
    private String langId;
    private String name;
    private String description;
}
