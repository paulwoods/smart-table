package org.mrpaulwoods.filtering.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SearchService {

    public <T> SearchResult<T> search(
            JpaSpecificationExecutor<T> repository,
            List<SearchColumn> smartColumns,
            Pageable pageable,
            List<String> filters
    ) {

        Specification<T> specification = createSpecification(filters);
        Page<T> page = repository.findAll(specification, pageable);
        return new SearchResult<>(smartColumns, page);
    }

    private <T> Specification<T> createSpecification(List<String> filters) {

        if (null == filters || filters.isEmpty()) {
            return null;
        }

        List<SearchSpecification<T>> specs = filters.stream()
                .map(SearchFilter::create)
                .filter(Objects::nonNull)
                .map(SearchSpecification<T>::new)
                .toList();

        Specification<T> spec = null;

        for (SearchSpecification<T> s : specs) {
            if (spec == null) {
                spec = s;
            } else {
                spec = spec.and(s);
            }
        }

        return spec;
    }

}


