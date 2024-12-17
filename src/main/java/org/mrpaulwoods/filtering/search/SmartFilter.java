package org.mrpaulwoods.filtering.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartFilter {

    private String key;
    private String operation;
    private String value;

    public static SmartFilter build(String text) {
        if (null == text) {
            return null;
        }

        // split the filter on the pipe
        // sometimes the pipe is encoded, so decode it.
        String[] items = text.replace("%7C", "|").replace("%7c", "|").split("\\|");
        if (items.length == 3) {
            return new SmartFilter(items[0], items[1], items[2]);
        } else {
            return null;
        }
    }

}
