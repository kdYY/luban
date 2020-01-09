package com.cloud.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName DashboardCurriculumScoreTable
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/12
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_curriculum_score")
public class DashboardCurriculumScore extends Model<DashboardCurriculumScore> implements Serializable {

    private static final long serialVersionUID = -6994829415730764935L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer dashboardCurriculumId;//看板课目基本信息id
    private Integer dashboardCurriculumProgressId;//看板课程分数过程id
    /*
    成绩类型(1 器材成绩，2 课目使用时间，3 自定义)
     */
    private Integer scoreType;
    private Integer equipmentCode;//器材code
    /*
    计算方法  1 按命中法数，2 按命中部位，3 按命中时间
     */
    private Integer computeType;
    private Integer hitNum;//命中数
    private Integer score;//单个器材得分
    /*
    是否计算过 用于器材成绩中案命中时间，如果计算一次就不允许再计算  默认否（0）
     */
    private boolean isCompute;
    /*
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
    /*
    信号集合
     */
    @TableField(exist = false)
    private List<DashboardCurriculumSignal> dashboardCurriculumSignalList;
    /*
    分数详细集合，主要提供自定义得分项
     */
    @TableField(exist = false)
    private List<DashboardCurriculumScoreDetail> dashboardCurriculumScoreDetailList;
}
