package com.cloud.dashboard.entity;

/**
 * @ClassName DashboardCurriculumSignal
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/12
 * @Version V1.0
 **/

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

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("dashboard_curriculum_signal")
public class DashboardCurriculumSignal extends Model<DashboardCurriculumSignal> implements Serializable {
    private static final long serialVersionUID = -6048982692607545270L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    看板课程分数过程id
     */
    private Integer dashboardCurriculumProgressId;
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
