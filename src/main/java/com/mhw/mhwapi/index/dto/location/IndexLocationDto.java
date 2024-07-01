package com.mhw.mhwapi.index.dto.location;

import com.mhw.mhwapi.index.dto.IndexEntryDto;
import lombok.Data;

@Data
public class IndexLocationDto extends IndexEntryDto {

    public Integer orderId;
    public String nameDe;
    public String nameEn;
}
