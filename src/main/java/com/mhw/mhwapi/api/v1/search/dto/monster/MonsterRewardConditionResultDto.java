package com.mhw.mhwapi.api.v1.search.dto.monster;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class MonsterRewardConditionResultDto implements DocumentResultDto {

    Integer id;
    String nameDe;
    String nameEn;
}
