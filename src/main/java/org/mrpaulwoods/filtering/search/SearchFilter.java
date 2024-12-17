package org.mrpaulwoods.filtering.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public record SearchFilter (

    String key,
    String operation,
    String value
) {

    public static SearchFilter create(String text) {

        if (null == text) {
            return null;
        }

        // split the filter on the pipe
        // sometimes the pipe is encoded, so decode it.
        String[] items = text
                .replace("%7C", "|")
                .replace("%7c", "|")
                .split("\\|");

        if (items.length == 3) {
            return new SearchFilter(items[0], items[1], items[2]);
        } else {
            return null;
        }
    }

    public List<String> valueAsList() {
        return Arrays.asList(value.split(";"));
    }

}
