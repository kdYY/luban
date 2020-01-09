package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName ContestMonitor
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_detail_monitor")
public class ContestDetailMonitor extends Model<ContestDetailMonitor> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
        竞赛id
        */
    private Integer contestId;
    /*
    竞赛详细id
    */
    private Integer contestDetailId;
    /*
    录像名
     */
    private String name;
    /*
    录像url
     */
    private String monitorUrl;
    /**
     * 创建时间
     */
    @JsonIgnore
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
