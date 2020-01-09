package com.cloud.file.utils;

import com.cloud.base.entity.SysDictItem;
import com.cloud.file.service.IDictItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @ClassName DictUtils
 * @Description: 刷新字典详细表数据
 * @Author kevins
 * @Date 2019/10/17
 * @Version V1.0
 **/
@Component
@Slf4j
public class DictUtils {

    @Autowired
    private IDictItemService iDictItemService;
    private HashMap<String, List<SysDictItem>> sysDictItemHashMap;
    private static final ReentrantLock LOCK = new ReentrantLock(true);

    public HashMap<String, List<SysDictItem>> getSysDictItem() {
        if (sysDictItemHashMap == null) {
            LOCK.lock();
            try {
                if (sysDictItemHashMap == null) {
                    sysDictItemHashMap = new HashMap<>(256);
                    refreshSysDictItem();
                }
            } finally {
                LOCK.unlock();
            }

        }
        return sysDictItemHashMap;
    }

    public void refreshSysDictItem() {
        sysDictItemHashMap = (HashMap<String, List<SysDictItem>>) iDictItemService.list().stream().collect(Collectors.groupingBy(SysDictItem::getItemText));

    }
}
