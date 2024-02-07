package com.mhw.mhwapi.api.v1.item.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class ItemTextDto implements Serializable {

    private String name;
    private String description;
}
