package com.mhw.mhwapi.common;

import lombok.Data;

import java.util.List;


@Data
public class SearchCriteria {

    private List<Filter> filters;

    public SearchCriteria(List<Filter> filters) {
        this.filters = filters;
    }
}
