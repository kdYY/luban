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
 * @ClassName Equipment
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/6
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("equipment")
public class Equipment extends Model<Equipment> implements Serializable {
    private static final long serialVersionUID = 2366896263954097943L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /*
    设备编号
     */
    private Integer equipmentCode;
    /*
    类型
     */
    private Integer type;

    /*
    操作人
     */
    private String userName;
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
    状态
     */
    @TableField(exist = false)
    private String state;
    /*
    电量
     */
    @TableField(exist = false)
    private String electric;
}
