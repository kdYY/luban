package com.cloud.maintenance.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName EquipmentRecordVO
 * @Description: 设备详细
 * @Author kevins
 * @Date 2019/11/6
 * @Version V1.0
 **/
@Data
public class EquipmentRecordVO implements Serializable {

    private static final long serialVersionUID = -976507017242664146L;

    private String time;
    //竞赛名
    private String contestName;
    //课目名
    private String curriculumName;
    //训练名
    private String trainingName;
    //竞赛id
    private Integer contestId;
    //课目id
    private Integer curriculumId;
    //训练id
    private Integer trainingId;
}
