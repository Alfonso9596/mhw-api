package com.mhw.mhwapi.api.v1.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDto<E extends DocumentResultDto> implements Serializable {

    private RestPage<E> documents;
}
