package org.mrpaulwoods.filtering.search;

public record SearchColumn(
        String id,
        String title,
        boolean enableFilter
) {
}
