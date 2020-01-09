package com.cloud.maintenance.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.maintenance.entity.Drill;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName DrillRecordVO
 * @Description: 训练记录响应体
 * @Author kevins
 * @Date 2019/12/31
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class DrillRecordVO implements Serializable {
    private static final long serialVersionUID = -6152267354889964703L;
    /*
    训练id
     */
    private Integer drillId;
    /*
    课目id
     */
    private Integer curriculumId;
    /*
    课目详细信息
     */
    private CurriculumVO curriculumVO;
    /*
    组训次数
     */
    private Integer count;
    /*
    最后一次训练时间
     */
    private LocalDateTime lastTime;
    /*
    列表数据
     */
    private IPage<Drill> drillIPage;

}
