package org.mrpaulwoods.filtering.search;

import lombok.Getter;

@Getter
public enum Operation {
    IN("in"),
    EQUALS("="),
    NOT_EQUALS("<>"),
        LESS("<"),
        GREATER(">"),
        LESS_EQUALS("<="),
        GREATER_EQUALS(">="),
        CONTAINS("contains")
    ;

    private final String text;

    Operation(String text) {
        this.text = text;
    }

    public static Operation fromText(String text) {
        for (Operation b : Operation.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
