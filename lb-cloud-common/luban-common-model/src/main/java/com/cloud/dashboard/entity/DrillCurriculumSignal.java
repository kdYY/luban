package com.cloud.dashboard.entity;

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
 * @ClassName DrillCurriculumSignal
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("drill_curriculum_signal")
public class DrillCurriculumSignal extends Model<DrillCurriculumSignal> implements Serializable {
    private static final long serialVersionUID = 6060985243060530065L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer drillId;
    /*
    看板课程分数过程id
     */
    private Integer drillCurriculumProgressId;
    /*
    靶机code
     */
    private Integer equipmentCode;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
    /*
    类别1（1，2，3，4，5，6，7，8，9，10环） 类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）类别5 224
     */
    @TableField("`signal`")
    private Integer signal;//信号
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
