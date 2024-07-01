package com.mhw.mhwapi.index.dto.monster;

import com.mhw.mhwapi.index.dto.IndexEntryDto;
import lombok.Data;

@Data
public class IndexMonsterBreakDto extends IndexEntryDto {

    public Integer monsterId;
    public String nameDe;
    public String nameEn;
    public Integer flinch;
    public Integer wound;
    public Integer sever;
    public String extract;
}
