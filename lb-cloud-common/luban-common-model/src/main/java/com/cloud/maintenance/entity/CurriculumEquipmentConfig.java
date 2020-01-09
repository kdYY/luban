package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CurriculumEquipmentConfig
 * @Description: 维护课目器材 对应的动作
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("curriculum_equipment_config")
public class CurriculumEquipmentConfig implements Serializable {

    private static final long serialVersionUID = 1795129568981218781L;
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
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
     器材名称
     */
    private String name;
    /*
    器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer action;
    /*
    逻辑运算符  && ||
    */
    private String sign;
    /*
    摇摆详细
     */
    private String swingDetail;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
    @TableField(exist = false)
    private List<CurriculumEquipmentTrigger> curriculumEquipmentTriggerList;
}
