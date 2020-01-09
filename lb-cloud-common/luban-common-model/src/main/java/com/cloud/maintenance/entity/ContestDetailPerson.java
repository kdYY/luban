package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @ClassName ContestDetailPerson
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("contest_detail_person")
public class ContestDetailPerson extends Model<ContestDetailPerson> implements Serializable {

    private static final long serialVersionUID = 1414346524775824404L;
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
    出场顺序
     */
    @TableField("`order`")
    private Integer order;
    /*
    证件号码
     */
    private String identificationNumber;
    /*
    部门id
     */
    private Integer deptId;
    /*
    人员类型
     */
    private Integer type;
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
