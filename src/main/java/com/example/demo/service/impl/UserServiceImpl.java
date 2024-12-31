package com.example.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.ImportUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.EasyPoiUtils;
import com.example.demo.validator.UserExcelValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    private final UserExcelValidator userExcelValidator;
    public UserServiceImpl(UserExcelValidator userExcelValidator){
        this.userExcelValidator = userExcelValidator;
    }


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

    @Override
    public Map<String, Object> importExcelOverwriteExisting(MultipartFile file, boolean overwriteExisting) {
        Map<String, Object> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        List<User> validData = new ArrayList<>();
        Set<String> branchNamesInExcel = new HashSet<>();
        Set<String> branchNamesToDelete = new HashSet<>();

        try (InputStream inputStream = file.getInputStream()){
            // 导入 Excel 数据
            ImportParams params = new ImportParams();
            // 表头行数
            params.setHeadRows(1);
            // 标题行数
            params.setTitleRows(1);

            List<ImportUser> customerInfoList = ExcelImportUtil.importExcel(inputStream, ImportUser.class, params);

            for (ImportUser customer : customerInfoList) {
                branchNamesInExcel.add(customer.getOrgName());
            }
            // 检查数据库中是否存在对应的“机构名称”数据
            List<String> existingBranchNames = this.findExistingNames(this,branchNamesInExcel);
            if (!existingBranchNames.isEmpty()) {
                if(overwriteExisting){
                    // 标记为待删除
                    branchNamesToDelete.addAll(existingBranchNames);
                }else{
                    // 如果存在重复的name数据，返回提示
                    result.put("status", "exists");
                    result.put("message", "以下机构名称已经存在：" + String.join(", ", branchNamesToDelete));
                    result.put("existingNames", branchNamesToDelete);
                    return result;
                }
            }

            // 获取所有字典数据
            Map<String, Map<String, String>> dictionaryMap = new HashMap<>();
//            dictionaryMap.put("address", repCountryService.getDictMap("corporate-type", 36));

            // 校验数据
            LocalDateTime date = LocalDateTime.now();
            for (int i = 0; i < customerInfoList.size(); i++) {
                ImportUser customer = customerInfoList.get(i);
                List<String> rowErrors = userExcelValidator.validate(customer,dictionaryMap);

                // 判断是否重复（根据唯一字段）
//                if (customerRepository.existsByCertNo(customer.getCertNo())) {
//                    rowErrors.add("证件号码已存在，重复数据");
//                }

                if (!rowErrors.isEmpty()) {
                    errors.add("第 " + (i + 3) + " 行: " + String.join("; ", rowErrors));
                } else {
                    User user = new User();
                    BeanUtils.copyProperties(customer, user);
                    user.setCreateTime(date);
                    validData.add(user);
                }
            }

            // 如果有校验错误，返回错误信息
            if (!errors.isEmpty()) {
                result.put("status", "fail");
                result.put("errors", errors);
                return result;
            }

            // 如果没有校验错误，删除已存在的分支机构数据
            if (overwriteExisting && !branchNamesToDelete.isEmpty()) {
                for (String orgName : branchNamesToDelete) {
                    this.remove(new QueryWrapper<User>().eq("org_name", orgName));
                }
            }

            Map<String, List<User>> groupedData = validData.stream()
                    .collect(Collectors.groupingBy(User::getAddress));
            groupedData.forEach((address, group) -> {
                int[] counter = {1}; // 流水号从 1 开始
                group.forEach(item -> {
                    String serialNo = String.format("%02d", counter[0]++); // 格式化为两位数
                    item.setNo("CNTR" + serialNo); // 设置流水号
                });
            });
            // 存入数据库
            this.saveBatch(validData,1000);

            result.put("status", "success");
            result.put("data", validData);

        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "导入失败：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
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

    public <T> List<String> findExistingNames(IService<T> mapper, Set<String> branchNamesInExcel) {
        if (branchNamesInExcel == null || branchNamesInExcel.isEmpty()) {
            return Collections.emptyList();
        }

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT org_name");
        queryWrapper.in("org_name", branchNamesInExcel);

        return mapper.listObjs(queryWrapper)
                .stream()
                .map(obj -> (String) obj)
                .collect(Collectors.toList());
    }
}
