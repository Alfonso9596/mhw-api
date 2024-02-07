package com.mhw.mhwapi.domain.entities.locationItem;

import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import com.mhw.mhwapi.domain.entities.locationText.LocationTextEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = LocationItemEntity.TABLE_NAME)
@Getter
@Setter
public class LocationItemEntity {

    static final String TABLE_NAME = "location_item";

    @Id
    private Integer id;
    @ManyToOne(targetEntity = LocationTextEntity.class)
    @JoinColumn(name = "location_id")
    private LocationTextEntity location;
    private Integer area;
    @Column(name = "class")
    private String group;
    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
    private Integer stack;
    private Integer percentage;
    private Integer nodes;
}
