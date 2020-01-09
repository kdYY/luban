package com.cloud.maintenance.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DrillListVO
 * @Description: 训练列表/训练大厅 查询响应体
 * @Author kevins
 * @Date 2019/12/29
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class DrillListVO implements Serializable {

    private static final long serialVersionUID = -2509984563549431279L;
    /*
    训练id
     */
    private Integer drillId;
    /*
    课目id
     */
    private Integer curriculumId;
    /*
    课目名
     */
    private String curriculumName;
    /*
    课目类型1 单人，2 小组
     */
    private Integer curriculumType;
    /*
    组训次数
     */
    private Integer count;
    /*
    最后一次训练时间
     */
    private LocalDateTime lastTime;
    /*
    组训人 只有大厅显示
     */
    private String userName;
    /*
    人员数 只有我的列表状态正在进行中的显示
     */
    private Integer userCount;
    /*
     状态 1 未开始 2 进行中 3完成， 只有我的列表显示
     */
    private Integer state;

}
