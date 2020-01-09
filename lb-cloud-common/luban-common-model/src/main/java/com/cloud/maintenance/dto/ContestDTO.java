package com.cloud.maintenance.dto;

import com.cloud.maintenance.entity.ContestDetail;
import com.cloud.maintenance.entity.ContestPerson;
import com.cloud.maintenance.entity.ContestTime;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ContestDTO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
public class ContestDTO implements Serializable {
    private static final long serialVersionUID = 7815607104917006330L;
    private Integer id;
    /*
    竞赛名
     */
    private String name;
    /*
    描述
     */
    private String describe;
    /*
    激活方式 1:手动。2:自动
     */
    private Integer activationType;
    /*
    竞赛时间表
     */
    private List<ContestTime> contestTimeList;
    /*
    竞赛单位和人员
     */
    private List<ContestPerson> contestPersonList;
    /*
    竞赛详细
     */
    private List<ContestDetail> contestDetailList;
    /*
    日期范围
     */
    private String daysTime;
    /*
    比赛日
     */
    private Integer days;
    /*
    队员数
     */
    private Integer personNum;
}
