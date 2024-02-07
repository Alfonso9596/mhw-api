package com.mhw.mhwapi.api.v1.language.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class LanguageDto implements Serializable {

    private String id;
    private String name;
}
