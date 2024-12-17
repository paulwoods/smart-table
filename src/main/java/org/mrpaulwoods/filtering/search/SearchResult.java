package org.mrpaulwoods.filtering.search;

import org.springframework.data.domain.Page;

import java.util.List;

public record SearchResult<T>(
        List<SearchColumn> columns,
        Page<T> page
) {

}
