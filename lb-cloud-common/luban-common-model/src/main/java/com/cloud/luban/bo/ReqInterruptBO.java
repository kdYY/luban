package com.cloud.luban.bo;

import com.cloud.dashboard.entity.DashboardCurriculumScore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName ReqInterruptBO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/29
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class ReqInterruptBO {
    /*
    过程小组id
     */
    private Integer dashboardCurriculumProgressId;
    /*
    靶机编码
     */
    private Integer equipmentCode;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
    /*
    部位（1头，2胸，3肩，4腹，5腿）,如果是环靶，例如160是10环10点，145是9环1点
     */
    private Integer part;
    /*
    该小组的该器材的计算方式集合
     */
    private List<DashboardCurriculumScore> dashboardCurriculumScoreList;

}
