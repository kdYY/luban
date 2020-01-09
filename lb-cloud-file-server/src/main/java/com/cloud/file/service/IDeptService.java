package com.cloud.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.entity.SysDept;

public interface IDeptService extends IService<SysDept> {

    Integer selectIdByParentIdName(Integer parentId, String name);
}
