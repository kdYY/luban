package com.cloud.file.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.cloud.base.entity.SysDictItem;
import com.cloud.base.vo.SysUserImportVo;
import com.cloud.core.exception.BaseException;
import com.cloud.file.utils.DictUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysUserExcelHandler
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Slf4j
@Component
public class SysUserExcelHandler extends ExcelDataHandlerDefaultImpl<SysUserImportVo> {
    @Autowired
    DictUtils dictUtils;

    @Override
    public Object importHandler(SysUserImportVo obj, String name, Object value) {
        List<SysDictItem> list = dictUtils.getSysDictItem().get(name);
        if (list.size() == 0) {
            log.error("获取数据字典信息错误");
            throw new BaseException("获取数据字典信息错误");
        }
        List<SysDictItem> filterList = list.stream().filter(a -> a.getItemValue().equals(value)).collect(Collectors.toList());
        if (filterList.size() == 0) {
            log.error("错误值：" + name + ":" + value);
            throw new BaseException("当前列：" + name + "有错误值为" + value);
        }
        return super.importHandler(obj, name, value);
    }
}
