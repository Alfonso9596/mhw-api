package com.mhw.mhwapi.domain.entities.locationText;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = LocationTextEntity.TABLE_NAME)
@Getter
@Setter
public class LocationTextEntity {

    static final String TABLE_NAME = "location_text";

    @Id
    private Integer id;
    private Integer orderId;
    private String langId;
    private String name;
}
