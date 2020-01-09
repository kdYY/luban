package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DrillEquipment
 * @Description: 训练器材
 * @Author kevins
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("drill_equipment")
public class DrillEquipment extends Model<DrillEquipment> implements Serializable {
    private static final long serialVersionUID = -6829027205323062672L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer drillId;
    /*
    器材名
     */
    private String name;
    /*
    器材编码
     */
    private Integer equipmentCode;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer target_type;
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
}
