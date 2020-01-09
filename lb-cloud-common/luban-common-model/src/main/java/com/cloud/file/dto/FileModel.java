package com.cloud.file.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @Author kevins
 * @Description 附件实体类模型
 * @Date 5:06 下午 2019/12/15
 **/
@Data
public class FileModel implements Serializable {

    private static final long serialVersionUID = -6212008701023948679L;
    /**
     * 附件原名(不带路径)
     */
    private String fileName;

    /**
     * 附件下载地址
     */
    private String fileUrl;

    /**
     * 附件大小(单位:B)
     */
    private Long fileSize;


}
