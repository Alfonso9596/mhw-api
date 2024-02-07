package com.mhw.mhwapi.domain.entities.tool;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = ToolEntity.TABLE_NAME)
@Getter
@Setter
public class ToolEntity {

    static final String TABLE_NAME = "tool";

    @Id
    private Integer id;
    private Integer orderId;
    private String toolType;
    private Integer duration;
    private Integer durationUpgraded;
    private Integer recharge;
    private Integer slot1;
    private Integer slot2;
    private Integer slot3;
    private String iconColor;
}
