package com.cloud.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DashboardCurriculumScoreDetail
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/21
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_curriculum_score_detail")
public class DashboardCurriculumScoreDetail extends Model<DashboardCurriculumScoreDetail> implements Serializable {

    private static final long serialVersionUID = 1349499050131326862L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
     课目id
     */
    private Integer dashboardCurriculumScoreId;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
    类别1（1，2，3，4，5，6，7，8，9，10环），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    单发分数、部位分数、基准分数 扩大10倍
     */
    private Integer score;
    /*
    分数上限 扩大10倍
     */
    private Integer scoreUpperLimit;
    /*
    分数下限 扩大10倍
     */
    private Integer scoreLowerLimit;
    /*
    基准时间 毫秒
     */
    private Integer baseTime;
    /*
    计时器材，和器材配置的name 含义相同
     */
    private Integer timingEquipmentCode;
    /*
    计时动作 1 起，2 倒
     */
    private Integer timingAction;
    /*
    附加增加分数 扩大10倍
     */
    private Integer addScore;
    /*
    附加减少分数 扩大10倍
     */
    private Integer subScore;
    /*
    自定义成绩名称
     */
    private String customName;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
