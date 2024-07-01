package com.mhw.mhwapi.api.v1.search.dto.monster;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class MonsterRewardResultDto implements DocumentResultDto {

    Integer id;
    Integer monsterId;
    Integer conditionId;
    String rank;
    Integer itemId;
    Integer stack;
    Integer percentage;
}
