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
 * @ClassName ActionSendLog
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/8
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("action_send_log")
public class ActionSendLog extends Model<ActionSendLog> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    看板课程分数过程id
     */
    private Integer dashboardCurriculumProgressId;
    /*
    指令id
     */
    private Integer directiveId;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶 ,5红外
     */
    private Integer targetType;
    /*
    器材code
    */
    private Integer equipmentCode;
    /*
    器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer action;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
