package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
@Data
public class PageResult<E> implements Serializable {
    private static final long serialVersionUID = -146111865024913407L;

    /**
     * 第几页
     */
    private int pageNo;
    /**
     * 每页多少数据
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private long totalRecords;
    /**
     * 返回记录列表
     */
    private List<E> records;

    /**
     *
     * @return
     */
    public List<E> getRecords() {
        return records == null ? new ArrayList<>(0) : records;
    }
}
