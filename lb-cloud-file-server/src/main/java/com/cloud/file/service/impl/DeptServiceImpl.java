package com.cloud.file.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.entity.SysDept;
import com.cloud.file.mapper.DeptMapper;
import com.cloud.file.service.IDeptService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DeptServiceImpl
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/10
 * @Version V1.0
 **/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, SysDept> implements IDeptService {


    @Override
    public Integer selectIdByParentIdName(Integer parentId, String name) {
        return baseMapper.selectOne(Wrappers.<SysDept>query().lambda().select(SysDept::getDeptId).eq(SysDept::getParentId, parentId).eq(SysDept::getName, name).eq(SysDept::getDelFlag, 0)).getDeptId();
    }
}
