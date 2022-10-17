package com.example.demo.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author lenovo
 */
public class EasyPoiUtils {
    /**
     * 功能描述：根据接收的Excel文件来导入多个sheet,根据索引可返回一个集合
     *
     * @param inputStream  excel输入流
     * @param sheetIndex 导入sheet索引
     * @param titleRows  表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass  Excel实体类
     */
    public static <T> List<T> importExcel(InputStream inputStream, int sheetIndex, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        // 根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
        ImportParams params = new ImportParams();
        // 第几个sheet表页
        params.setStartSheetIndex(sheetIndex);
        //设置表标题行数
        params.setTitleRows(titleRows);
        //设置表头行数
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            //读取的excel数据集合
            list = ExcelImportUtil.importExcel(inputStream, pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
