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
 * @ClassName DashboardDept
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/12
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_dept")
public class DashboardDept extends Model<DashboardDept> implements Serializable {
    private static final long serialVersionUID = -7669647542021409660L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer contestId;//竞赛id
    private Integer deptId;//部门id
    private Integer score;//部门总分数，扩大十倍
    private Integer ranking;//排名
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
    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
}
