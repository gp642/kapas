package com.kapas.vendor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedResponse<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
