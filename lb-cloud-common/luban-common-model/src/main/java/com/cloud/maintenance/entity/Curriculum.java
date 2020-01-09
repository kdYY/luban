package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName Curriculum
 * @Description: 课目基础表
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("curriculum")
public class Curriculum extends Model<Curriculum> implements Serializable {

    private static final long serialVersionUID = -2183033836253524230L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
     课目名称
     */
    private String name;
    /*
    课目类型1 单人，2 小组
     */
    private Integer type;
    /*
    人数
     */
    private Integer number;
    /*
    描述
     */
    @TableField("`desc`")
    private String desc;
    /*
    课目图片
     */
    private String image;
    /*
    预计时间，秒
     */
    private Integer expectedTime;
    /*
    使用枪械
     */
    private String arms;
    /*
    胸靶板数量
     */
    private Integer chestTargetNum;
    /*
    半身靶板数量
     */
    private Integer halfBodyTargetNum;
    /*
    侧身靶板数量
     */
    private Integer sideTargetNum;
    /*
    红传感器数量
     */
    private Integer infraredSensorNum;
    /*
    弹药数量
     */
    private Integer ammunitionNum;
    /*
    完成方式 1手动 ，2 自动
     */
    private Integer finishType;
    /*
    完成器材的名字
     */
    private String finishEquipment;
    /*
    完成器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer finishAction;
    /*
    训练数量
     */
    private Integer drillNum;
    /*
    竞赛数量
     */
    private Integer contestNum;
    /**
     * 操作人ID
     */
    private String userName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;

}
