package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName CurriculumScoreDetail
 * @Description: 维护课目成绩的详细规则
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("curriculum_score_detail")
public class CurriculumScoreDetail implements Serializable {

    private static final long serialVersionUID = 764716967123003161L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
     课目id
     */
    private Integer curriculumId;
    /*
     课目成绩id
     */
    private Integer curriculumScoreId;
    /*
    靶板类别 1 胸环靶，2半身靶，3侧身靶，4人质靶
     */
    private Integer targetType;
    /*
    类别1（ 1头，2胸，3肩），类别2（1头，2胸，3肩，4腹）， 类别3（1头，2胸，3肩，4腹，5腿）
     */
    private Integer part;
    /*
    单发分数、部位分数、基准分数 扩大10倍
     */
    private Integer score;
    /*
    分数上限 扩大10倍
     */
    private Integer scoreUpperLimit;
    /*
    分数下限 扩大10倍
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
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;
}
