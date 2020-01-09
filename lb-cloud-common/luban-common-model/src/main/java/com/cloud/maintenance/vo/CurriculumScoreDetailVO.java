package com.cloud.maintenance.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CurriculumScoreDetailVO
 * @Description: 成绩详细规则vo
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
public class CurriculumScoreDetailVO implements Serializable {


    private static final long serialVersionUID = 2640677114675808138L;

    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
     触发部位 类别1（ 1头，2胸，3肩），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    单发分数、部位分数、基准分数 扩大10倍
     */
    private Integer score;
    /*
    分数上限  扩大10倍
     */
    private Integer scoreUpperLimit;
    /*
    分数下限  扩大10倍
     */
    private Integer scoreLowerLimit;
    /*
    基准时间 毫秒
     */
    private Integer baseTime;
    /*
    计时器材，和器材配置的name 含义相同
     */
    private String timingEquipment;
    /*
    计时动作 1 起，2 倒
     */
    private Integer timingAction;
    /*
    附加增加分数 扩大10倍
     */
    private Integer addScore;
    /*
    附加减少分数 扩大10倍
     */
    private Integer subScore;
    /*
    自定义成绩名称
     */
    private String customName;
}
