package com.cloud.maintenance.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CurriculumEquipmentTriggerVO
 * @Description: 器材触发条件vo
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
public class CurriculumEquipmentTriggerVO implements Serializable {

    private static final long serialVersionUID = 2059566042281100647L;
    /*
        触发方式 1 手动，2 设备感应，3 靶机
         */
    private Integer triggerWay;
    /*
    关联设备名称，与课目配置表name含义相同
     */
    private String name;
    /*
    触发条件，1 命中，2 起立后，3 倒下后
     */
    private Integer triggerCondition;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
     触发部位 类别1（ 1头，2胸，3肩），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    延迟时间 毫秒
     */
    private Integer delay;

}
