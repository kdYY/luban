package com.cloud.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.entity.SysDictItem;
import com.cloud.file.mapper.DictItemMapper;
import com.cloud.file.service.IDictItemService;
import org.springframework.stereotype.Service;

/**
 * @Classname SysDictItemServiceImpl
 * @Description TODO
 * @Author kevins
 * @Date 2019-09-02 18:07
 * @Version 1.0
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, SysDictItem> implements IDictItemService {
}
