package com.cloud.maintenance.vo;

import com.cloud.maintenance.dto.SwingDetailDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CurriculumEquipmentConfigVO
 * @Description: 器材配置动作
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
public class CurriculumEquipmentConfigVO implements Serializable {
    private static final long serialVersionUID = 1869887611630424245L;
    /*
        课目id
         */
    private Integer curriculumId;
    /*
     器材名称
     */
    private String name;
    /*
    器材动作 1 起， 2 倒，3 摇摆，4 旋转显 ，5旋转隐 ,224 红外触发
     */
    private Integer action;
    /*
    逻辑运算符  && ||
    */
    private String sign;
    /*
    摇摆详细
     */
    private List<SwingDetailDTO> swingDetail;
    /*
    触发规则集合
     */
    private List<CurriculumEquipmentTriggerVO> curriculumEquipmentTriggerList;

}
