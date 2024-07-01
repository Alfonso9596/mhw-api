package com.mhw.mhwapi.api.v1.search.dto.monster;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class MonsterHitzoneResultDto implements DocumentResultDto {

    Integer id;
    Integer monsterId;
    String nameDe;
    String nameEn;
    Integer cut;
    Integer impact;
    Integer shot;
    Integer fire;
    Integer water;
    Integer ice;
    Integer thunder;
    Integer dragon;
    Integer ko;
}
