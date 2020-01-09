package com.cloud.luban.vo;

import lombok.Data;

/**
 * @ClassName EquipmentIngVO
 * @Description: 器材进行中实体类，用于websocket发送
 * @Author kevins
 * @Date 2019/11/28
 * @Version V1.0
 **/
@Data
public class EquipmentIngVO {
    /*
    初始化 1，成绩/响应 2 ，分数 3 ，查询状态 4
     */
    private Integer type;
    private Integer equipmentCode;
    private Integer action;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外(暂时除外)
     */
    private Integer targetType;
    /*
    环数  912  9环12点 ，10 10环
     */
    private Integer ring;
    /*
    （1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    分数
     */
    private Integer score;
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
