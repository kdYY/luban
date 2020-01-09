package com.cloud.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.entity.SysJob;

public interface IJobService extends IService<SysJob> {
    Integer selectJobIdByJobName(String jobName);
}
