package com.mhw.mhwapi.index.dto.monster;

import com.mhw.mhwapi.index.dto.IndexEntryDto;
import lombok.Data;

@Data
public class IndexMonsterRewardDto extends IndexEntryDto {

    public Integer monsterId;
    public Integer conditionId;
    public String rank;
    public Integer itemId;
    public Integer stack;
    public Integer percentage;
}
