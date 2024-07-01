package com.mhw.mhwapi.api.v1.search.dto.location;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import lombok.Data;

@Data
public class LocationResultDto implements DocumentResultDto {

    Integer id;
    Integer orderId;
    String nameDe;
    String nameEn;
}
