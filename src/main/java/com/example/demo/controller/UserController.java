package com.example.demo.controller;

import com.example.demo.common.ResultResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * excel批量导入用户
     */
    @GetMapping("/import")
    public ResultResponse<Boolean> importUsers(@RequestParam("file") MultipartFile file) {
        if(userService.importUsers(file)){
            return ResultResponse.success();
        }
        return ResultResponse.error("操作失败");
    }
}
