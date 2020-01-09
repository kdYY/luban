package com.cloud.maintenance.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DrillRecordDTO
 * @Description: 训练记录参数体
 * @Author kevins
 * @Date 2019/12/31
 * @Version V1.0
 **/
@Data
public class DrillRecordDTO implements Serializable {

    private static final long serialVersionUID = 6280072646680307374L;
    /*
    登录人证件号
     */
    private String identificationNumber;
    /*
    课目id
     */
    private Integer curriculumId;
}
