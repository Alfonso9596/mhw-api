package com.mhw.mhwapi.domain.entities;

import com.mhw.mhwapi.common.Filter;
import com.mhw.mhwapi.common.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntitySpecification<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySpecification.class);

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
            case IN -> {
                return ((root, query, criteriaBuilder) -> {
                    CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(root.get(filter.getField()));
                    for (Integer i : Arrays.stream(filter.getValue().split(",")).map(Integer::parseInt).toList()) {
                        inClause.value(i);
                    }
                    return inClause;
                });
            }
            default -> {
                return null;
            }
        }
    }
}
