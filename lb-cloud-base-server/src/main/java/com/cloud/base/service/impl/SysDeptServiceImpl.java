package com.cloud.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.dto.DeptDTO;
import com.cloud.base.entity.SysDept;
import com.cloud.base.entity.SysUser;
import com.cloud.base.mapper.SysDeptMapper;
import com.cloud.base.service.ISysDeptService;
import com.cloud.base.service.ISysUserService;
import com.cloud.base.vo.SysDeptTreeVo;
import com.cloud.core.exception.BaseException;
import com.cloud.core.utils.MmcUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {
    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public boolean save(SysDept entity) {
        return super.save(entity);
    }

    @Override
    public List<SysDept> selectDeptList() {
        List<SysDept> depts = baseMapper.selectList(Wrappers.<SysDept>lambdaQuery().select(SysDept::getDeptId, SysDept::getName, SysDept::getParentId, SysDept::getSort, SysDept::getCreateTime));
        List<SysDept> sysDepts = depts.stream()
                .filter(sysDept -> sysDept.getParentId() == 0 || ObjectUtil.isNull(sysDept.getParentId()))
                .peek(sysDept -> sysDept.setLevel(0))
                .collect(Collectors.toList());

        MmcUtil.findChildren(sysDepts, depts);
        return sysDepts;
    }


    @Override
    public boolean updateDeptById(DeptDTO entity) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(entity, sysDept);
        sysDept.setUpdateTime(LocalDateTime.now());
        return this.updateById(sysDept);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        // 部门层级删除
        List<Integer> idList = new ArrayList<>();
        checkChildrenExists((Integer) id, idList);
        // 删除自己
        idList.add((Integer) id);
        //查询部门下有人员不允许删除
        List<SysUser> userInfo = (List<SysUser>) iSysUserService.listByIds(idList);
        if (userInfo.size() > 0) {
            throw new BaseException("部门下有用户无法删除");
        }
        idList.forEach(deptId -> {
            this.update(Wrappers.<SysDept>lambdaUpdate().set(SysDept::getDelFlag, "1").eq(SysDept::getDeptId, deptId));
        });
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batchDeleteDeptByIds(List<Integer> ids) {
        List<Integer> idList = new ArrayList<>();
        for (Integer id : ids) {
            idList.add(id);
            this.checkChildrenExists(id, idList);
        }
        //查询部门下有人员不允许删除
        List<SysUser> userInfo = (List<SysUser>) iSysUserService.listByIds(idList);
        if (userInfo.size() > 0) {
            throw new BaseException("部门下有用户无法删除");
        }
        idList.forEach(deptId -> {
            this.update(Wrappers.<SysDept>lambdaUpdate().set(SysDept::getDelFlag, "1").eq(SysDept::getDeptId, deptId));
        });
        return true;
    }

    @Override
    public String selectDeptNameByDeptId(int deptId) {
        return baseMapper.selectOne(Wrappers.<SysDept>query().lambda().select(SysDept::getName).eq(SysDept::getDeptId, deptId)).getName();
    }

    @Override
    public List<SysDept> selectDeptListBydeptName(String deptName) {
        return null;
    }

    @Override
    public List<Integer> selectDeptIds(int deptId) {
        SysDept department = this.getDepartment(deptId);
        List<Integer> deptIdList = new ArrayList<>();
        if (department != null) {
            deptIdList.add(department.getDeptId());
            addDeptIdList(deptIdList, department);
        }
        return deptIdList;
    }

    @Override
    public List<SysDeptTreeVo> queryDepartTreeList() {
        List<SysDept> depts = baseMapper.selectList(Wrappers.<SysDept>lambdaQuery().select(SysDept::getDeptId, SysDept::getName, SysDept::getParentId, SysDept::getSort, SysDept::getRemark, SysDept::getCreateTime));

        List<SysDeptTreeVo> collect = depts.stream()
                .filter(sysDept -> sysDept.getParentId() == 0 || ObjectUtil.isNull(sysDept.getParentId()))
                .peek(sysDept -> sysDept.setLevel(0))
                .map(sysDept -> {
                    SysDeptTreeVo sysDeptTreeVo = new SysDeptTreeVo();
                    BeanUtil.copyProperties(sysDept, sysDeptTreeVo);
                    sysDeptTreeVo.setKey(sysDept.getDeptId());
                    sysDeptTreeVo.setValue(String.valueOf(sysDept.getDeptId()));
                    sysDeptTreeVo.setTitle(sysDept.getName());
                    return sysDeptTreeVo;
                })
                .collect(Collectors.toList());

        MmcUtil.findDeptTreeChildren(collect, depts);
        return collect;
    }


    /**
     * 根据部门ID获取该部门及其下属部门树
     */
    private SysDept getDepartment(Integer deptId) {
        List<SysDept> departments = baseMapper.selectList(Wrappers.<SysDept>query().select("dept_id", "name", "parent_id", "sort", "create_time"));
        Map<Integer, SysDept> map = departments.stream().collect(
                Collectors.toMap(SysDept::getDeptId, department -> department));

        for (SysDept dept : map.values()) {
            SysDept parent = map.get(dept.getParentId());
            if (parent != null) {
                List<SysDept> children = parent.getChildren() == null ? new ArrayList<>() : parent.getChildren();
                children.add(dept);
                parent.setChildren(children);
            }
        }
        return map.get(deptId);
    }

    private void addDeptIdList(List<Integer> deptIdList, SysDept department) {
        List<SysDept> children = department.getChildren();

        if (children != null) {
            for (SysDept d : children) {
                deptIdList.add(d.getDeptId());
                addDeptIdList(deptIdList, d);
            }
        }
    }

    /**
     * delete 方法调用
     *
     * @param id
     * @param idList
     */
    private void checkChildrenExists(int id, List<Integer> idList) {
        List<SysDept> deptList = this.list(Wrappers.<SysDept>query().lambda().eq(SysDept::getParentId, id));
        if (CollUtil.isNotEmpty(deptList)) {
            for (SysDept dept : deptList) {
                idList.add(dept.getDeptId());
                this.checkChildrenExists(dept.getDeptId(), idList);
            }
        }
    }


}
