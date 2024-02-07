package com.mhw.mhwapi.util;

import com.mhw.mhwapi.common.Page;
import com.mhw.mhwapi.common.Pageable;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@NoArgsConstructor
public class QueryPageResponseUtil {

    public static <T> Page<T> convertPageResponse(List<T> data, org.springframework.data.domain.Page<?> source, PageRequest pageable) {

        var result = new Page<T>();

        if (source.getTotalElements() < 1) {
            return result;
        }

        result.setTotalElements(source.getTotalElements());
        result.setPageable(Pageable
                .builder()
                .last(source.isLast())
                .first(source.isFirst())
                .pageSize(pageable.getPageSize())
                .currentPage(pageable.getPageNumber() + 1)
                .totalPages(source.getTotalPages())
                .build());

        result.setData(data);
        return result;
    }
}
