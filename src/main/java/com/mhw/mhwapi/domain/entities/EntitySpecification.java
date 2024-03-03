package com.mhw.mhwapi.domain.entities;

import com.mhw.mhwapi.common.Filter;
import com.mhw.mhwapi.common.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntitySpecification<T> {

    public Specification<T> specificationBuilder(SearchCriteria searchCriteria) {
        if (Objects.nonNull(searchCriteria) && !CollectionUtils.isEmpty(searchCriteria.getFilters())) {
            List<Filter> filters = searchCriteria.getFilters();
            List<Specification<T>> specifications = filters.stream()
                    .map(this::createSpecification)
                    .collect(Collectors.toList());
            return Specification.allOf(specifications);
        }
        return null;
    }

    private Specification<T> createSpecification(Filter filter) {
        switch (filter.getOperator()) {
            case EQUALS -> {
                return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(filter.getField()), filter.getValue()));
            }
            case NOT_EQUALS -> {
                return ((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(filter.getField()), filter.getValue()));
            }
            case GREATER_THAN -> {
                return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getValue()));
            }
            case LESS_THAN -> {
                return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getValue()));
            }
            case LIKE -> {
                return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(filter.getField()), "%" + filter.getValue() + "%"));
            }
            default -> {
                return null;
            }
        }
    }
}
