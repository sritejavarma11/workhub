package com.teja.workhub.dto;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> data;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private boolean last;

    public PaginatedResponse(List<T> data, int page, int size, int totalElements, int totalPages, boolean last) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }
}
