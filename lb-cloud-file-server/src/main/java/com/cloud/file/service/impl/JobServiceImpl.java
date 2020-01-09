package com.cloud.file.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.entity.SysJob;
import com.cloud.file.mapper.JobMapper;
import com.cloud.file.service.IJobService;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobServiceImpl
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/10
 * @Version V1.0
 **/
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, SysJob> implements IJobService {
    @Override
    public Integer selectJobIdByJobName(String jobName) {
        return baseMapper.selectOne(Wrappers.<SysJob>lambdaQuery().select(SysJob::getId).eq(SysJob::getJobName, jobName)).getId();
    }
}
