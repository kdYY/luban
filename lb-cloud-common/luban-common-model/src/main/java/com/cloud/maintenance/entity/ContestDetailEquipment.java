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
 * @ClassName ContestDetailEquipment
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_detail_equipment")
public class ContestDetailEquipment extends Model<ContestDetailEquipment> implements Serializable {
    private static final long serialVersionUID = 824501796143168157L;
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
    器材名
     */
    private String name;
    /*
    器材编码
     */
    private Integer equipmentCode;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
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
