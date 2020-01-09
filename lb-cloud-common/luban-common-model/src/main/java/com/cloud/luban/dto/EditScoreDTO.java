package com.cloud.luban.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName EditScoreDTO
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/25
 * @Version V1.0
 **/
@Data
public class EditScoreDTO implements Serializable {

    private static final long serialVersionUID = -4599049986324780305L;
    private Integer contestId;
    private Integer contestDetailId;
    private Integer progressId;
    private Integer dashboardScoreId;
    /*
    课目类型1 单人，2 小组
     */
    private Integer type;
    /*
    如果是单人比赛，需要传
     */
    private String identificationNumber;
    /*
    如果是小组比赛，需要传
     */
    private Integer deptId;
    private Integer score;
}
