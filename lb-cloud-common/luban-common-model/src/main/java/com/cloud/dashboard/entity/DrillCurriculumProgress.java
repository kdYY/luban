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
 * @ClassName DrillCurriculumProgress
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/27
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("drill_curriculum_progress")
public class DrillCurriculumProgress extends Model<DrillCurriculumProgress> implements Serializable {
    private static final long serialVersionUID = 1375270746371261859L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer drillId;
    /*
        出场顺序
         */
    @TableField("`order`")
    private Integer order;
    /*
    人的证件号，多人用逗号隔开
     */
    private String identificationNumber;
    /*
    部门id，多人用逗号隔开
     */
    private String deptId;
    /*
    开始时间
     */
    private LocalDateTime startTime;
    /*
    结束时间
     */
    private LocalDateTime endTime;
    /*
    使用时间 秒
     */
    private Long useTime;
    /*
    总分 扩大十倍
     */
    private Integer totalScore;
    /*
    状态 1排队默认 2正在进行 3完成
     */
    private Integer state;
    /*
    命中弹药，使用弹药
     */
    private Integer ammunitionNum;
    /*
    命中率 命中弹药/总弹药
     */
    private Integer hitRate;
    /*
    课目分数规则集合的json格式
     */
    private String scoreJson;
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
    /*
    用户名
     */
    @TableField(exist = false)
    private String userName;
    /**
     * 非数据库字段
     * 部门名称 xx/xx/xx
     */
    @TableField(exist = false)
    private String deptName;


}
