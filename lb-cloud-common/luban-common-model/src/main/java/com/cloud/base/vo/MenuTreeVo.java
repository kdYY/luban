package com.cloud.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Classname MenuTreeVo
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-30 16:51
 * @Version 1.0
 */
@Data
public class MenuTreeVo {

    /**
     * 对应SysDepart中的id字段,前端数据树中的key
     */
    private Integer key;
    /**
     * 对应SysDepart中的id字段,前端数据树中的value
     */
    private String value;
    /**
     * 对应depart_name字段,前端数据树中的title
     */
    private String title;

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 菜单等级
     */
    private Integer level;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeVo> children;
}
