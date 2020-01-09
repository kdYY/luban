package com.cloud.maintenance.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SwingDTO
 * @Description: 摇摆详细
 * @Author kevins
 * @Date 2019/12/4
 * @Version V1.0
 **/
@Data
public class SwingDetailDTO implements Serializable {
    private static final long serialVersionUID = -6281518666409019770L;
    /*
    次数
     */
    private Integer th;
    /*
    方向 1左，2右
     */
    private Integer direction;
    /*
     档位 1小，2大
     */
    private Integer gear;
    /*
    停留时间 毫秒
     */
    private Integer residenceTime;
    /*
    间隔时间 毫秒
     */
    private Integer intervalTime;
}
