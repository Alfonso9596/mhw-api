package com.mhw.mhwapi.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class Page<T> {

    public static final int DEFAULT_TOTAL = 0;

    private Pageable pageable;
    private Sort sort;
    private long totalElements = DEFAULT_TOTAL;
    private List<T> data = new ArrayList<>();

    public <D extends Serializable> Page<D> cloneWithData(List<D> data) {
        Page page = new Page();
        page.setPageable(this.pageable);
        page.setSort(this.sort);
        page.setTotalElements(this.totalElements);
        page.setData(data);
        return page;
    }

    public <D extends Serializable> Page<D> map(Function<T,D> function) {
        List<D> convertedData = getData().stream()
                .map(function)
                .collect(Collectors.toList());
        return cloneWithData(convertedData);
    }
}
