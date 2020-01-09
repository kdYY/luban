package com.cloud.file.controller;

import com.cloud.base.entity.SysUser;
import com.cloud.core.exception.CheckedException;
import com.cloud.core.utils.R;
import com.cloud.file.service.IPersonnelImport;
import com.cloud.file.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName PersonnelImportController
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Api(description = "人员导入")
//为测试使用跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class PersonnelImportController {
    @Autowired
    private IPersonnelImport iPersonnelImport;

    @ApiOperation("用户信息文件导入")
    @PostMapping(value = "/excelImport")
    public R uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new CheckedException("文件为空");
        }
        return R.ok(iPersonnelImport.personnelExcelImport(file));
    }

    @ApiOperation("预留用户信息文件导入")
    @GetMapping("/excelExport")
    public void export(HttpServletResponse response) {
        System.out.println(1);
        // 模拟从数据库获取需要导出的数据
        // 导出操作
        ExcelUtils.exportExcel(null, "easypoi导出功能", "人员信息", SysUser.class, "测试user.xls", response);

    }

}
