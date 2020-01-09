package com.cloud.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ExportExcelTemplateController
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Api(description = "导出人员模版")
//为测试使用跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class ExportExcelTemplateController {

    @ApiOperation("导出人员模版")
    @GetMapping(value = "/downLoadTemplate")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileDownName = "PersonnelTemplate.xlsx";
        log.info("下载模板文件名称：" + fileDownName);
        try {
            InputStream fis = ExportExcelTemplateController.class.getResourceAsStream("/doc/PersonnelTemplate.xlsx");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("bin");
            String fileNames = fileDownName;
            String agent = request.getHeader("USER-AGENT");
            String codedfilename = "";
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
            response.addHeader("Content-Disposition", "attachment; filename=\"" + codedfilename + "\"");
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            log.debug("下载模板文件报错" + e.getMessage(), e);
        }
    }
}
