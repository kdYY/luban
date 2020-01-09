package com.cloud.luban.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName TriggerControl
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/19
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("trigger_control")
public class TriggerControl extends Model<TriggerControl> implements Serializable {
    private static final long serialVersionUID = 5859755735051448940L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer contestId;
    private Integer contestDetailId;
    /*
    课目器材配置id
     */
    private Integer curriculumEquipmentConfigId;
    /*
    触发条件，1 命中，2 起立后，3 倒下后
     */
    private Integer triggerCondition;
    /*
    器材靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶 ,5红外
     */
    private Integer equipmentTargetType;
    /*
    器材code
     */
    private Integer equipmentCode;
    /*
    触发器材靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶 ,5红外
     */
    private Integer triggerEquipmentTargetType;
    /*
    触发器材code
     */
    private Integer triggerEquipmentCode;
    /*
    器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer action;
    /*
    摇摆详细信息,如果动作是摇摆就把摇摆信息遍历成List<SwingDetail>
    List<SwingDetailDTO> swingDetail = gson.fromJson(str, new TypeToken<List<SwingDetailDTO>>() {}.getType());
     */
    private String swingDetail;
    /*
    触发方式 1 手动，2 设备感应，3 靶机
     */
    private Integer triggerWay;

    /*
     触发部位 类别1（ 1头，2胸，3肩），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    逻辑运算符  && ||
    */
    private String sign;
    /*
    延迟时间 毫秒
     */
    private Integer delay;
    /*
    是否触发
     */
    private boolean isTrigger;
    /*
    是否执行动作
     */
    private boolean isExecute;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
