package com.cloud.dashboard.entity;

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
 * @ClassName DashboardEquipmentInit
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/18
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_equipment_init")
public class DashboardEquipmentInit extends Model<DashboardEquipmentInit> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer contestDetailId;
    /*
    器材编码
     */
    private Integer equipmentCode;
    /*
    器材动作1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐
     */
    private Integer initAction;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
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
}
