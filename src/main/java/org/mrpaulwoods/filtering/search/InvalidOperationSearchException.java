package org.mrpaulwoods.filtering.search;

public class InvalidOperationSearchException extends SearchException {

    public InvalidOperationSearchException(String operation) {
        super("invalid operation: " + operation);
    }
}
