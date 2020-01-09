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
 * @ClassName CurriculumEquipmentInit
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/8
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("curriculum_equipment_init")
public class CurriculumEquipmentInit implements Serializable {
    private static final long serialVersionUID = 3275125465065762933L;
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
     器材名称
     */
    private String name;
    /*
    器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐
     */
    private Integer initAction;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
