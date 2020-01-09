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

/**
 * @ClassName DashboardDeptCurriculum
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/12
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_dept_curriculum")
public class DashboardDeptCurriculum extends Model<DashboardDeptCurriculum> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer contestId;//竞赛id
    private Integer deptId;//部门id
    private Integer dashboardCurriculumId;//看板课目快照表id
    private Integer curriculumType;//课目类型 1 单人，2 小组
    private Integer number;//单个组的人数 默认1
    private Integer peopleNum;//当前单位下当前课目参加的总人数
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
    课目快照名
     */
    @TableField(exist = false)
    private String curriculumName;
}
