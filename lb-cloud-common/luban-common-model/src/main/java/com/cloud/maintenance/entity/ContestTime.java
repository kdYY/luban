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
 * @ClassName ContestTime
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_time")
public class ContestTime extends Model<ContestTime> implements Serializable {
    private static final long serialVersionUID = -4686157078126702322L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛id
    */
    private Integer contestId;
    /*
    竞赛头时间2019-11-20  08:00
     */
    private String tipTime;
    /*
    竞赛尾时间2019-11-20  12:00
     */
    private String tailTime;
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
