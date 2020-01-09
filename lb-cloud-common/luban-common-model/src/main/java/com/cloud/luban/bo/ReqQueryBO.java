package com.cloud.luban.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ReqQueryBO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/1
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class ReqQueryBO {
    /*
    靶机编码
     */
    private Integer equipmentCode;
    /*
    请求项 0获取靶机全部参数，1靶机状态，2电量，3信号强度
     */
    private Integer option;
    /*
    0在线 ，2离线
     */
    private Integer state;
    /*
    百分比 64=100%
     */
    private Integer electric;
    /*
    信号强度 50=80%
     */
    private Integer dBm;
}
