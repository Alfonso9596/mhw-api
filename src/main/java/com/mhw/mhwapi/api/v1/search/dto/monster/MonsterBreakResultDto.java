package com.mhw.mhwapi.api.v1.search.dto.monster;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class MonsterBreakResultDto implements DocumentResultDto {

    Integer id;
    Integer monsterId;
    String nameDe;
    String nameEn;
    Integer flinch;
    Integer wound;
    Integer sever;
    String extract;
}
