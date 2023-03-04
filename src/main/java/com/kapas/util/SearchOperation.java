package com.kapas.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.Path;

@Getter
@Setter
@AllArgsConstructor
public class SearchOperation<T> {
    private SearchOperationEnum operation;
    private Path<T> path;
    private T value;
    private T otherValue;

    public SearchOperation(SearchOperationEnum operation, Path<T> path, T value) {
        this.operation = operation;
        this.path = path;
        this.value = value;
    }
}
