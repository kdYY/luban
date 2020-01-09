package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ContestDetail
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_detail")
public class ContestDetail extends Model<ContestDetail> implements Serializable {
    private static final long serialVersionUID = 510584710148729967L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛id
     */
    private Integer contestId;
    /*
    场地id
     */
    private Integer placeId;
    /*
    课目id
     */
    private Integer curriculumId;
    /*
    课目类型 1单人，2小组
     */
    private Integer curriculumType;
    /*
    管理员
     */
    private String identificationNumber;
    /*
    开始时间
     */
    private String startTime;
    /*
    预计完成时间
     */
    private String expectFinishTime;
    /*
    状态
     */
    private Integer state;
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
    /*
    竞赛详细人员
    非数据库字段
     */
    @TableField(exist = false)
    private List<ContestDetailPerson> contestDetailPersonList;
    /*
    竞赛详细器材配置
    非数据库字段
     */
    @TableField(exist = false)
    private List<ContestDetailEquipment> contestDetailEquipmentList;
    /*
    竞赛详细视频链接
    非数据库字段
     */
    @TableField(exist = false)
    private List<ContestDetailMonitor> contestDetailMonitorList;
    /*
    课目name
    */
    @TableField(exist = false)
    private String curriculumName;
    /*
    场地名称
     */
    @TableField(exist = false)
    private String placeName;
}
