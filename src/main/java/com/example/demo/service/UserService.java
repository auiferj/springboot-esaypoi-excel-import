package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
public interface UserService extends IService<User> {
    /**
     * excel批量导入用户
     * @param file
     * @return
     */
    Boolean importUsers(MultipartFile file);

    /***
     * excel多sheet导入
     * @param file
     * @throws IOException
     */
    void importForSheetUsers(MultipartFile file) throws IOException;
}
