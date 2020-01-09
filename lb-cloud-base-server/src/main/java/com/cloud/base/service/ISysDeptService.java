package com.cloud.base.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.dto.DeptDTO;
import com.cloud.base.entity.SysDept;
import com.cloud.base.vo.SysDeptTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 保存部门信息
     *
     * @return
     */
    @Override
    boolean save(SysDept entity);

    /**
     * 查询部门信息
     *
     * @return
     */
    List<SysDept> selectDeptList();

    /**
     * 更新部门
     *
     * @param entity
     * @return
     */
    boolean updateDeptById(DeptDTO entity);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 批量删除部门
     *
     * @param ids
     * @return
     */
    boolean batchDeleteDeptByIds(List<Integer> ids);

    /**
     * 根据部门id查询部门名称
     *
     * @param deptId
     * @return
     */
    String selectDeptNameByDeptId(int deptId);

    /**
     * 根据部门名称查询
     *
     * @param deptName
     * @return
     */
    List<SysDept> selectDeptListBydeptName(String deptName);

    /**
     * 通过此部门id查询于此相关的部门ids
     *
     * @param deptId
     * @return
     */
    List<Integer> selectDeptIds(int deptId);


    /**
     * 查询部门信息 部门树
     *
     * @return
     */
    List<SysDeptTreeVo> queryDepartTreeList();


}
