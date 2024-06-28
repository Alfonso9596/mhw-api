package com.mhw.mhwapi.domain.entities.location;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = LocationEntity.TABLE_NAME)
@Getter
@Setter
public class LocationEntity {

    static final String TABLE_NAME = "location";

    @Id
    private Integer id;
    private Integer orderId;
}
