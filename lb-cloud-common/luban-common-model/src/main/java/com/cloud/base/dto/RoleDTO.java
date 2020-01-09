package com.cloud.base.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Classname UserDTO
 * @Description 角色Dto
 * @Author kevins
 * @Date 2019-04-23 21:26
 * @Version 1.0
 */
@Data
public class RoleDTO {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String delFlag;
    private int dsType;
    List<Integer> permissionIds;
//    List<Integer> roleDepts;


}
