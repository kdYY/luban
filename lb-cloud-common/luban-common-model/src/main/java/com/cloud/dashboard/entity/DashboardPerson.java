package com.cloud.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.base.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DashboardPerson
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/13
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_person")
public class DashboardPerson extends Model<DashboardPerson> implements Serializable {
    private static final long serialVersionUID = 2374231740072690987L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛id
    */
    private Integer contestId;

    /*
    证件号码
     */
    private String identificationNumber;
    /*
    部门id
     */
    private Integer deptId;
    /*
    个人总分
     */
    private Integer score;
    /*
    个人排名
     */
    private Integer ranking;
    /**
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
     * 用户名
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private SysUser sysUser;
    /*
    竞赛名字
     */
    @TableField(exist = false)
    private String contestName;
}
