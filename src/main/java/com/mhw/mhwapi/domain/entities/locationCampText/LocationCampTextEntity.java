package com.mhw.mhwapi.domain.entities.locationCampText;

import com.mhw.mhwapi.domain.entities.locationText.LocationTextEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = LocationCampTextEntity.TABLE_NAME)
@Getter
@Setter
public class LocationCampTextEntity {

    static final String TABLE_NAME = "location_camp_text";

    @Id
    private Integer id;
    @ManyToOne(targetEntity = LocationTextEntity.class)
    @JoinColumn(name = "location_id")
    private LocationTextEntity location;
    private String langId;
    private String name;
    private Integer area;
}
