package com.example.demo.validator;

import com.example.demo.entity.ImportUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
@Component
public class UserExcelValidator {

    public List<String> validate(ImportUser customer, Map<String, Map<String, String>> dictionaryMap) {
        List<String> errors = new ArrayList<>();

        //姓名
        if(StringUtils.isBlank(customer.getName())){
            errors.add("姓名不能为空");
        }
        //地址
        if(StringUtils.isBlank(customer.getAddress())){
            errors.add("地址不能为空");
        }else{
            Map<String, String> d1Map = dictionaryMap.get("address");
            if (!d1Map.containsKey(customer.getAddress())) {
                errors.add("地址不正确");
            }else {
                customer.setAddress(d1Map.get(customer.getAddress()));
            }
        }

        return errors;
    }

}
