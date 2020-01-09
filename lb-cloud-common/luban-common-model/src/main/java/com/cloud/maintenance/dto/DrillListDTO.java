package com.cloud.maintenance.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DrillListDTO
 * @Description: 训练列表/训练大厅 查询参数体
 * @Author kevins
 * @Date 2019/12/29
 * @Version V1.0
 **/
@Data
public class DrillListDTO implements Serializable {
    private static final long serialVersionUID = 5110411450134577468L;
    /*
    登录人证件号
     */
    private String identificationNumber;
    /*
    参数，如果登录人为空，课目名和组训人员都模糊，反之只有课目名模糊
     */
    private String param;
}
