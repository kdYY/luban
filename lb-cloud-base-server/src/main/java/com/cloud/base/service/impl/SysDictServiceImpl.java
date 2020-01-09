package com.cloud.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.dto.DictDTO;
import com.cloud.base.entity.SysDict;
import com.cloud.base.entity.SysDictItem;
import com.cloud.base.mapper.SysDictMapper;
import com.cloud.base.service.ISysDictItemService;
import com.cloud.base.service.ISysDictService;
import com.cloud.core.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-05-17
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    private ISysDictItemService iSysDictItemService;

    @Override
    public boolean save(SysDict entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateDict(DictDTO dictDto) {
//        if (ObjectUtil.isNull(dictDto.getValue())) {
//            // 先查询所有的含有的主键 然后批量修改
//            List<SysDict> sysDicts = baseMapper.selectList(Wrappers.<SysDict>lambdaQuery().select(SysDict::getId).eq(SysDict::getName, baseMapper.selectById(dictDto.getId()).getName()));
//            List<SysDict> collect = sysDicts.stream().map(sysDict1 -> {
//                SysDict sysDict = new SysDict();
//                sysDict.setId(sysDict1.getId());
//                sysDict.setName(dictDto.getName());
//                return sysDict;
//            }).collect(Collectors.toList());
//            return updateBatchById(collect);
//        }
        SysDict sysDict = new SysDict();
        BeanUtil.copyProperties(dictDto, sysDict);
        return baseMapper.updateById(sysDict) > 0;
    }


    @Override
    public boolean removeById(Serializable id) {
        int count = iSysDictItemService.count(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
        if (count > 0) {
            throw new BaseException("该字典详细有数据，无法删除");
        }
        return super.update(Wrappers.<SysDict>lambdaUpdate().set(SysDict::getDelFlag, "1").eq(SysDict::getId, id));
    }
}
