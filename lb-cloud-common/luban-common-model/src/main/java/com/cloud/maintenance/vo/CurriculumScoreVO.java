package com.cloud.maintenance.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CurriculumScoreVO
 * @Description: 成绩规则vo
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
public class CurriculumScoreVO implements Serializable {

    private static final long serialVersionUID = 5422339949640836056L;
    /*
    成绩类型(1 器材成绩，2 课目使用时间，3 自定义)
     */
    private Integer scoreType;
    /*
     器材名称，与课目配置表name含义一致
     */
    private String name;
    /*
    计算方法  1 按命中法数，2 按命中部位，3 按命中时间
     */
    private Integer computeType;
    /*
    成绩详细规则
     */
    private List<CurriculumScoreDetailVO> curriculumScoreDetailList;

}
