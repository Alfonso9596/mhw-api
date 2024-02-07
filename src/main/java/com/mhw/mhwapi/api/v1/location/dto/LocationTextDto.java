package com.mhw.mhwapi.api.v1.location.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class LocationTextDto implements Serializable {

    private Integer orderId;
    private String name;
}
