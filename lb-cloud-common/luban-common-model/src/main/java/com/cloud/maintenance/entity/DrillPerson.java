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
 * @ClassName DrillPerson
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("drill_person")
public class DrillPerson extends Model<DrillPerson> implements Serializable {
    private static final long serialVersionUID = -5920379167838300409L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer drillId;

    /*
    出场顺序
     */
    @TableField("`order`")
    private Integer order;
    /*
    证件号码
     */
    private String identificationNumber;
    /*
    部门id
     */
    private Integer deptId;
    /*
    人员类型
     */
    private Integer type;
    private LocalDateTime createTime;
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
}
