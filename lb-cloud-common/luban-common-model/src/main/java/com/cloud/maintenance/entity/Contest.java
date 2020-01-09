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
 * @ClassName Contest
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest")
public class Contest extends Model<Contest> implements Serializable {

    private static final long serialVersionUID = 2488779850583584189L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛名
     */
    private String name;
    /*
    描述
     */
    @TableField("`describe`")
    private String describe;
    /*
    激活方式 1:手动。2:自动
     */
    private Integer activationType;
    /*
    竞赛状态
     */
    private Integer state;
    /*
    激活时间
     */
    private String activationTime;
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
    日期范围
     */
    @TableField(exist = false)
    private String daysTime;
    /*
    比赛日
     */
    @TableField(exist = false)
    private Integer days;
    /*
    课目数
     */
    @TableField(exist = false)
    private Integer curriculumNum;
    /*
    队员数
     */
    @TableField(exist = false)
    private Integer personNum;


}
