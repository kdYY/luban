package com.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.dto.DictDTO;
import com.cloud.base.entity.SysDict;

import java.io.Serializable;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @Author kevins
 * @since 2019-05-17
 */
public interface ISysDictService extends IService<SysDict> {

    @Override
    boolean save(SysDict entity);

    /**
     * 修改字典
     *
     * @param dictDto
     * @return
     */
    boolean updateDict(DictDTO dictDto);


    /**
     * 根据主键Id删除字典
     *
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);
}
