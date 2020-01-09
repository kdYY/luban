package com.cloud.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Classname DeptTreeVo
 * @Description 构建部门树vo
 * @Author kevins
 * @Date 2019-06-09 15:15
 * @Version 1.0
 */
@Setter
@Getter
@ToString
public class DeptTreeVo {

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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptTreeVo> children;

}
