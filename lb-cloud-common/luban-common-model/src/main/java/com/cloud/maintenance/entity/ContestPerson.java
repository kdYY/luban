package com.cloud.maintenance.entity;

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
 * @ClassName ContestPerson
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_person")
public class ContestPerson extends Model<ContestPerson> implements Serializable {
    private static final long serialVersionUID = -1325096088931392220L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛id
    */
    private Integer contestId;
    /*
    部门ID
     */
    private Integer deptId;
    /*
    证件号码
     */
    private String identificationNumber;
    /*
    人员类型
     */
    private Integer type;
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
    /*
    人员类型名称
     */
    @TableField(exist = false)
    private String typeName;
    /*
    人名
     */
    @TableField(exist = false)
    private String userName;
    /*
    部门名
     */
    @TableField(exist = false)
    private String deptName;


}
