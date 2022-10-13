package com.example.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lenovo
 */
@Data
public class ImportUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * @Excel 作用在一个filed上面，对列的描述
     * @param name 列名
     * @param orderNum 下标，从0开始。
     */
    @Excel(name = "姓名", orderNum = "0")
    private String name;
    @Excel(name = "年龄", orderNum = "1")
    private Integer age;
    @Excel(name = "性别", orderNum = "2", replace = {"男_1","女_2"})
    private Integer sex;
    @Excel(name = "地址", orderNum = "3")
    private String address;
    @Excel(name = "用户描述", orderNum = "4")
    private String describes;
}
