package com.cloud.base.dto;

import lombok.Data;

/**
 * @Classname RepeatCheckDTO
 * @Description 重复校验DTO
 * @Author kevins
 * @Date 2019-09-02 10:35
 * @Version 1.0
 */
@Data
public class RepeatCheckDTO {

    /**
     * 指用户id 主要作用编辑情况过滤自己的校验
     */
    private Integer dataId;
    /**
     * 字段值 邮箱 手机号 用户名
     */
    private String fieldVal;


}
