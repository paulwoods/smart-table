package org.mrpaulwoods.filtering.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public record SearchSpecification<T>(SearchFilter filter) implements Specification<T> {

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {

        Operation op = Operation.fromText(filter.operation());
        if (null == op) {
            throw new InvalidOperationSearchException(filter.operation());
        }

        return switch (op) {
            case IN -> builder.in(root.get(filter.key())).value(filter.valueAsList());
            case EQUALS -> builder.equal(root.get(filter.key()), filter.value());
            case NOT_EQUALS -> builder.notEqual(root.get(filter.key()), filter.value());
            case LESS -> builder.lessThan(root.get(filter.key()), filter.value());
            case GREATER -> builder.greaterThan(root.get(filter.key()), filter.value());
            case LESS_EQUALS -> builder.lessThanOrEqualTo(root.get(filter.key()), filter.value());
            case GREATER_EQUALS -> builder.greaterThanOrEqualTo(root.get(filter.key()), filter.value());
            case CONTAINS -> builder.like(builder.lower(root.get(filter.key()).as(String.class)),
                    builder.lower(builder.literal("%" + filter.value().toLowerCase() + "%")));
        };

    }

}
