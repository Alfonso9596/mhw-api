package com.mhw.mhwapi.api.v1.monster.dto;

import com.mhw.mhwapi.api.v1.location.dto.LocationTextDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class MonsterHabitatDto implements Serializable {

    private LocationTextDto location;
    private String startArea;
    private String moveArea;
    private String restArea;
}
