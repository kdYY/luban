package com.cloud.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.entity.SysUser;
import com.cloud.base.vo.SysUserImportVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPersonnelImport extends IService<SysUser> {
    List<SysUserImportVo> personnelExcelImport(MultipartFile file);

    /**
     * @return java.util.List<com.mmc.base.entity.SysUser>
     * @Author kevins
     * @Description 获取所有证件号
     * @Date 2:50 下午 2019/10/18
     * @Param []
     **/
    List<SysUser> findIdentification();
}
