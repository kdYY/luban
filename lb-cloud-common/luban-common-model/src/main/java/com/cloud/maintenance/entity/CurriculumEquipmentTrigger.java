package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName CurriculumEquipmentTrigger
 * @Description: 器材触发条件
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("curriculum_equipment_trigger")
public class CurriculumEquipmentTrigger implements Serializable {
    private static final long serialVersionUID = -524467936877355910L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    课目id
     */
    private Integer curriculumId;
    /*
    课目器材配置id
     */
    private Integer curriculumEquipmentConfigId;
    /*
    触发方式 1 手动，2 设备感应，3 靶机
     */
    private Integer triggerWay;
    /*
    关联设备名称，与课目配置表name含义相同
     */
    private String name;
    /*
    触发条件，1 命中，2 起立后，3 倒下后
     */
    private Integer triggerCondition;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
     触发部位 类别1（ 1头，2胸，3肩），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    延迟时间 毫秒
     */
    private Integer delay;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
