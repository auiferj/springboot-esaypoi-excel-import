package com.example.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.entity.ImportUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.EasyPoiUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Boolean importUsers(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        //标题行设置为1行，默认是0，可以不设置。
        importParams.setTitleRows(1);
        // 表头设置为1行
        importParams.setHeadRows(1);
        try {
            //读取excel
            List<ImportUser> users = ExcelImportUtil.importExcel(file.getInputStream(), ImportUser.class, importParams);
            //批量插入
            boolean isSuccess = this.saveUsers(users);
            //返回结果
            return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void importForSheetUsers(MultipartFile file) throws IOException {
        //分批进行读取excel的sheet工作表.
        // 一般情况下，不同的sheet表一般对应的都是不同的数据内容.这里只做演示，没有制定几个不同的导入类。
        //读取第一个sheet表
        List<ImportUser> sheetOneUsers = EasyPoiUtils.importExcel(file.getInputStream(), 0, 1, 1, ImportUser.class);
        //读取第二个sheet表
        List<ImportUser> sheetTwoUsers = EasyPoiUtils.importExcel(file.getInputStream(), 1, 1, 1, ImportUser.class);
        //批量插入
        this.saveUsers(sheetOneUsers);
        this.saveUsers(sheetTwoUsers);
    }

    private boolean saveUsers(List<ImportUser> users){
        if(CollectionUtils.isNotEmpty(users)){
            List<User> list = new ArrayList<>();
            for (ImportUser importUser : users) {
                User user = new User();
                BeanUtils.copyProperties(importUser,user);
                user.setCreateTime(LocalDateTime.now());
                list.add(user);
            }
            return this.saveBatch(list);
        }
        return false;
    }
}
