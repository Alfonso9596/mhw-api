package com.mhw.mhwapi.index.dto.monster;

import com.mhw.mhwapi.index.dto.IndexEntryDto;
import lombok.Data;

@Data
public class IndexMonsterHitzoneDto extends IndexEntryDto {

    public Integer monsterId;
    public String nameDe;
    public String nameEn;
    public Integer cut;
    public Integer impact;
    public Integer shot;
    public Integer fire;
    public Integer water;
    public Integer ice;
    public Integer thunder;
    public Integer dragon;
    public Integer ko;
}
