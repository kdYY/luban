package com.cloud.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname SysDepartTreeVo
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-30 08:57
 * @Version 1.0
 */
@Setter
@Getter
@ToString
public class SysDeptTreeVo implements Serializable {

    /**
     * 对应SysDepart中的id字段,前端数据树中的key
     */
    private int key;
    /**
     * 对应SysDepart中的id字段,前端数据树中的value
     */
    private String value;
    /**
     * 对应depart_name字段,前端数据树中的title
     */
    private String title;

    /**
     * 部门主键ID
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String name;


    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private String delFlag;

    /**
     * 上级部门
     */
    private String parentName;
    /**
     * 等级
     */
    private Integer level;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysDeptTreeVo> children;
}
