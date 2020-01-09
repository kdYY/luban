package com.cloud.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.dashboard.entity.DashboardPerson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 岗位ID
     */
    private Integer jobId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-锁定
     */
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    private String name;//姓名
    private Integer policeRank;//警衔
    private Integer type;//人员类型
    private Integer politicalOutlook;//政治面貌
    private Integer nation;//民族
    private String birthday;
    private String identificationNumber;//证件号码
    private Integer height;//身高
    private Integer weight;//体重
    private String blood;//血型
    private String nativePlace;//籍贯
    private String sex;//性别
    private String id_card;//身份证
    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList;
    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
    ;
    /**
     * 非数据库字段
     * 岗位名称
     */
    @TableField(exist = false)
    private String jobName;
    /*
     * 非数据库字段
     * 人员类型名称
     */
    @TableField(exist = false)
    private String TypeName;
    /*
    日常训练次数
     */
    @TableField(exist = false)
    private Integer dailyCount;
    /*
    覆盖 几 个课目
     */
    @TableField(exist = false)
    private Integer curriculumCount;
    /*
    参加过 2 次比武竞赛
     */
    @TableField(exist = false)
    private Integer contestCount;
    /*
    分别为
     */
    @TableField(exist = false)
    private List<DashboardPerson> dashboardPersonList;
}
