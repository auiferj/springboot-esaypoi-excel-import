package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author lenovo
* @since 2022-10-13
*/
@Data
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "")
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private String id;

            @ApiModelProperty("姓名")
        @TableField("name")
    private String name;

            @ApiModelProperty("性别")
        @TableField("sex")
    private Integer sex;

            @ApiModelProperty("年龄")
        @TableField("age")
    private Integer age;

            @ApiModelProperty("地址")
        @TableField("address")
    private String address;

            @ApiModelProperty("用户描述")
        @TableField("describes")
    private String describes;

        @TableField("create_by")
    private String createBy;

        @TableField("create_time")
    private LocalDateTime createTime;

        @TableField("update_by")
    private String updateBy;

        @TableField("update_time")
    private LocalDateTime updateTime;


}