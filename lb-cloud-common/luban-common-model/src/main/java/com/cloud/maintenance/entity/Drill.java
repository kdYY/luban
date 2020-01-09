package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName Drill
 * @Description: 训练
 * @Author kevins
 * @Date 2019/12/26
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("drill")
public class Drill extends Model<Drill> implements Serializable {
    private static final long serialVersionUID = 2526186464597678261L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "课目id不能为空")
    private Integer curriculumId;
    /*
    课目类型1 单人，2 小组
     */
    @NotNull(message = "课目类型不能为空")
    private Integer curriculumType;
    /*
    管理员证件号
     */
    @NotNull(message = "管理员证件号不能为空")
    private String identificationNumber;
    /*
    开始时间
     */
    private LocalDateTime startTime;
    /*
    预计完成时间
     */
    private LocalDateTime expectFinishTime;
    /*
    状态 1 未开始 2 进行中 3完成
     */
    private Integer state;
    /*
    平均时间
     */
    private Integer avgTime;
    /*
    平均分数
     */
    private Integer avgScore;
    /*
    平均使用弹药
     */
    private Integer avgAmmunition;
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
    训练器材
     */
    @TableField(exist = false)
    private List<DrillEquipment> drillEquipmentList;
    /*
    训练人员
     */
    @TableField(exist = false)
    private List<DrillPerson> drillPersonList;
    /*
    人员数
    */
    @TableField(exist = false)
    private Integer userCount;
}
