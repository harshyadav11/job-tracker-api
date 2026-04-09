package com.jobtracker.dto;
public class ApiResponse<T> {

    private T data;
    private int page;
    private int size;
    private long total;

    public ApiResponse(T data, int page, int size, long total) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public T getData() { return data; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public long getTotal() { return total; }
}