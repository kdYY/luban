package com.cloud.maintenance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.cloud.maintenance.entity.CurriculumEquipmentConfig;
import com.cloud.maintenance.entity.CurriculumEquipmentInit;
import com.cloud.maintenance.entity.CurriculumScore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CurriculumVO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/26
 * @Version V1.0
 **/
@Data
public class CurriculumVO implements Serializable {

    private static final long serialVersionUID = -2184323913327197826L;
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
    预计时间，毫秒
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
    完成器材动作  1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer finishAction;
    /*
    日常数量
     */
    private Integer dailyNum;
    /*
    竞赛数量
     */
    private Integer contestNum;
    /**
     * 操作人ID
     */
    private String userName;

    private List<CurriculumEquipmentConfig> curriculumEquipmentConfigList;

    private List<CurriculumScore> curriculumScoreList;
    private List<CurriculumEquipmentInit> curriculumEquipmentInitList;
}
