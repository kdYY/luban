package com.cloud.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Classname UserDTO
 * @Description 用户Dto
 * @Author kevins
 * @Date 2019-04-23 21:26
 * @Version 1.0
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1816824604418586636L;
    private Integer userId;
    private String username;
    private String password;
    private Integer deptId;
    private Integer jobId;
    private String phone;
    private String email;
    private String avatar;
    private String lockFlag;
    private String name;//姓名
    private Integer policeRank;//警衔
    private Integer type;//人员类型
    private Integer politicalOutlook;//政治面貌
    private Integer nation;//民族
    private String birthday;//出生日期
    private String identificationNumber;//证件号码
    private Integer height;//身高
    private Integer weight;//体重
    private String blood;//血型
    private String nativePlace;//籍贯
    private String sex;//性别
    private String id_card;//身份证
    private String delFlag;
    private List<Integer> roleList;
    private Integer[] deptList;//前端传
    private Set<Integer> deptIdsList;//后端最全的deptids
}
