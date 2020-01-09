package com.cloud.maintenance.dto;

import com.cloud.maintenance.entity.ContestPerson;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ContestBaseDTO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/15
 * @Version V1.0
 **/
@Data
public class ContestBaseDTO implements Serializable {
    private static final long serialVersionUID = 1455519723195847426L;
    private String contestName;
    /*
    日期范围
     */
    private String daysTime;
    /*
    比赛日
     */
    private Integer days;
    /*
    状态
     */
    private Integer state;
    /*
    描述
     */
    private String describe;
    /*
    场地 逗号分隔
     */
    private String placeName;
    /*
    课目名字 逗号分隔
     */
    private String curriculumName;
    /*
    单位名字逗号分隔
     */
    private String deptName;
    /*
    所有人员集合
     */
    private List<ContestPerson> contestPersonList;
}
