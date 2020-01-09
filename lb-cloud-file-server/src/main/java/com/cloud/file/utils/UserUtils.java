package com.cloud.file.utils;

import com.cloud.file.service.IPersonnelImport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName UserUtils
 * @Description: 刷新人员表信息
 * @Author kevins
 * @Date 2019/10/18
 * @Version V1.0
 **/
@Component
@Slf4j
public class UserUtils {
    @Autowired
    IPersonnelImport iPersonnelImport;

    private HashSet<String> identificationList;

    private static final ReentrantLock LOCK = new ReentrantLock(true);

    public HashSet<String> getIPersonnel() {
        if (identificationList == null) {
            LOCK.lock();
            try {
                if (identificationList == null) {
                    identificationList = new HashSet<String>();
                    refreshIPersonnel();
                }
            } finally {
                LOCK.unlock();
            }

        }
        return identificationList;
    }

    public void refreshIPersonnel() {
        iPersonnelImport.findIdentification().forEach(a -> identificationList.add(a.getIdentificationNumber()));

    }


}
