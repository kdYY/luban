package com.cloud.maintenance.dto;

import com.cloud.maintenance.entity.CurriculumEquipmentInit;
import com.cloud.maintenance.vo.CurriculumEquipmentConfigVO;
import com.cloud.maintenance.vo.CurriculumScoreVO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CurriculumDTO
 * @Description: 课目维护dto
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
public class CurriculumDTO implements Serializable {

    private static final long serialVersionUID = 3608826295289114901L;
    private Integer id;
    @NotBlank(message = "课目名称不能为空")
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
    完成器材动作  1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐,224 红外触发
     */
    private Integer finishAction;
    /**
     * 操作人ID
     */
    private String userName;
    /*
    器材配置
     */
    private List<CurriculumEquipmentConfigVO> curriculumEquipmentConfigList;
    /*
    成绩规则
     */
    private List<CurriculumScoreVO> curriculumScoreList;
    /*
    设备初始化动作
     */
    private List<CurriculumEquipmentInit> curriculumEquipmentInitList;
}
