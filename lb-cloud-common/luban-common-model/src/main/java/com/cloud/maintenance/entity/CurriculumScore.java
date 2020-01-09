package com.cloud.maintenance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CurriculumScore
 * @Description: 维护课目成绩
 * @Author kevins
 * @Date 2019/9/24
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@TableName("curriculum_score")
public class CurriculumScore implements Serializable {
    private static final long serialVersionUID = 3364353553605631398L;
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
    @JsonIgnore
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    /*
     删除标识（0-正常,1-删除）
     */
    private String delFlag;

    @TableField(exist = false)
    private List<CurriculumScoreDetail> curriculumScoreDetailList;
}
