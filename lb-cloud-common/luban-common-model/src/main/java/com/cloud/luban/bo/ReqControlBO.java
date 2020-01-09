package com.cloud.luban.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ControlBO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/27
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class ReqControlBO {
    /*
    靶机编码
     */
    private Integer equipmentCode;
    /*
    指令id
     */
    private Integer directiveId;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶，5红外
     */
    private Integer targetType;
    /*
    命令字
     */
    private Integer command;
    /*
    器材动作1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer action;
}
