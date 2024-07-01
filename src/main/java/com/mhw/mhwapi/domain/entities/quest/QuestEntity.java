package com.mhw.mhwapi.domain.entities.quest;

import com.mhw.mhwapi.domain.entities.locationtext.LocationTextEntity;
import com.mhw.mhwapi.enums.QuestCategory;
import com.mhw.mhwapi.enums.QuestType;
import com.mhw.mhwapi.enums.converter.QuestCategoryConverter;
import com.mhw.mhwapi.enums.converter.QuestTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = QuestEntity.TABLE_NAME)
@Getter
@Setter
public class QuestEntity {

    static final String TABLE_NAME = "quest";

    @Id
    private Integer id;
    private Integer orderId;
    @Convert(converter = QuestCategoryConverter.class)
    private QuestCategory category;
    @Column(name = "class")
    private String group;
    private Integer stars;
    private Integer starsRaw;
    @Convert(converter = QuestTypeConverter.class)
    private QuestType questType;
    @ManyToOne(targetEntity = LocationTextEntity.class)
    @JoinColumn(name = "location_id")
    private LocationTextEntity location;
    private Integer zenny;
}
