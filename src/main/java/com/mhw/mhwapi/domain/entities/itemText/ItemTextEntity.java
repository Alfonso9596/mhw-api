package com.mhw.mhwapi.domain.entities.itemText;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = ItemTextEntity.TABLE_NAME)
@Getter
@Setter
public class ItemTextEntity {

    static final String TABLE_NAME = "item_text";

    @Id
    private Integer id;
    private String langId;
    private String name;
    private String description;
}
