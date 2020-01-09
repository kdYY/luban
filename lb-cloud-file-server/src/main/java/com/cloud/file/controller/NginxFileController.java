package com.cloud.file.controller;

import com.cloud.file.dto.FileModel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName NginxFileController
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/15
 * @Version V1.0
 **/
@Slf4j
@RestController
@Api(description = "Nginx文件操作")
public class NginxFileController {
    private static final DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    @Value("${file.path}")
    private String filePath;
    @Value("${file.returnPath}")
    private String returnPath;


    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public FileModel fileUpload(@RequestParam("fileName") MultipartFile file, String fileDirectory) {
        String deposeFilesDir = filePath;
        String accessFileUrlDir = returnPath;
        if (fileDirectory != null && !"".equals(fileDirectory.trim())) {
            deposeFilesDir = filePath + fileDirectory;
            accessFileUrlDir = returnPath + fileDirectory;
        }
        String pathAll = "";
        StringBuffer sb = new StringBuffer();
        sb.append("fileDelete>>>fileOriginalName>>>" + file.getOriginalFilename());
        Long startTime = System.currentTimeMillis();
        log.info(sb.toString());
        FileModel fileModel = new FileModel();
        Long size = file.getSize();
        fileModel.setFileSize(size);
        // 获取附件原名(有的浏览器如chrome获取到的是最基本的含 后缀的文件名,如myImage.png)
        // 获取附件原名(有的浏览器如ie获取到的是含整个路径的含后缀的文件名，如C:\\Users\\images\\myImage.png)
        String fileName = file.getOriginalFilename();
        try {
            // 如果是获取的含有路径的文件名，那么截取掉多余的,只剩下文件名和后缀名
            if (fileName.indexOf("\\") > 0) {
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
            }
            fileModel.setFileName(fileName);
            // 当文件有后缀名时
            if (fileName.lastIndexOf(".") >= 0) {
                int index = fileName.lastIndexOf(".");
                String fileNameOne = fileName.substring(0, index);
                String fileNameTwo = fileName.substring(index + 1);
                fileName = fileNameOne + "_" + LocalDateTime.now().format(simpleDateFormat) + "." + fileNameTwo;
            }
            // 当文件无后缀名时(如C盘下的hosts文件就没有后缀名)
            if (fileName.indexOf(".") < 0) {
                fileName = fileName + "_" + LocalDateTime.now().format(simpleDateFormat);
            }
            // 返回前端路径
            log.info("Receiving file save path-{},File size-{},file pathAll-{}", deposeFilesDir, size, pathAll);
            File dest = new File(deposeFilesDir + fileName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 保存文件
            file.transferTo(dest);
            Runtime.getRuntime().exec("chmod 744 -R " + dest.getPath());
            fileModel.setFileUrl(accessFileUrlDir + fileName);
        } catch (IllegalStateException e) {
            log.error(sb.toString() + ":" + e.getMessage(), e.getCause());
        } catch (IOException e) {
            log.error(sb.toString() + ":" + e.getMessage(), e.getCause());
        } catch (Exception e) {
            log.error(sb.toString() + ":" + e.getMessage(), e.getCause());
        }
        Long endTime = System.currentTimeMillis();
        Double timeConsumption = (endTime - startTime) * 1.0 / 1000;
        log.info("上传此附件消耗时间(秒):{}", timeConsumption);
        return fileModel;
    }
}
