package org.mrpaulwoods.filtering.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SmartSpecification<T> implements Specification<T> {

    protected SmartFilter filter;

    @SuppressWarnings("unused")
    public SmartSpecification() {
    }

    public SmartSpecification(SmartFilter filter) {
        this.filter = filter;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        try {

            switch (filter.getOperation().toLowerCase()) {

                case "in":// where the value is a semicolon-separated list of values
                    List<String> values = Arrays.asList(filter.getValue().split(";"));
                    return builder.in(root.get(filter.getKey())).value(values);
                case "=":
                    return builder.equal(root.get(filter.getKey()), filter.getValue());
                case "<>":
                    return builder.notEqual(root.get(filter.getKey()), filter.getValue());
                case "<":
                    return builder.lessThan(root.get(filter.getKey()), filter.getValue());
                case ">":
                    return builder.greaterThan(root.get(filter.getKey()), filter.getValue());
                case "<=":
                    return builder.lessThanOrEqualTo(root.get(filter.getKey()), filter.getValue());
                case ">=":
                    return builder.greaterThanOrEqualTo(root.get(filter.getKey()), filter.getValue());
                case "contains":
                    return builder.like(builder.lower(root.get(filter.getKey()).as(String.class)),
                            builder.lower(builder.literal("%" + filter.getValue().toLowerCase() + "%")));
                default:
                    throw new RuntimeException("invalid operation " + filter.getOperation());
            }
        } catch (NumberFormatException e) {
            log.info(e.getMessage(), e);
        }

        return null;
    }

}
