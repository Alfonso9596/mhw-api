package com.mhw.mhwapi.api.v1.search.dto.monster;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class MonsterHabitatResultDto implements DocumentResultDto {

    Integer id;
    Integer monsterId;
    Integer locationId;
    String startArea;
    String moveArea;
    String restArea;
}
