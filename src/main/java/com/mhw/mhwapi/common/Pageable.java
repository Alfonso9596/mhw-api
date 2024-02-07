package com.mhw.mhwapi.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pageable {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private boolean last;
    private boolean first;
}
