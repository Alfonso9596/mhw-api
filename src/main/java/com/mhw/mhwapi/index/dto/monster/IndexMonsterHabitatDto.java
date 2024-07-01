package com.mhw.mhwapi.index.dto.monster;

import com.mhw.mhwapi.index.dto.IndexEntryDto;
import lombok.Data;

@Data
public class IndexMonsterHabitatDto extends IndexEntryDto {

    public Integer monsterId;
    public Integer locationId;
    public String startArea;
    public String moveArea;
    public String restArea;
}
