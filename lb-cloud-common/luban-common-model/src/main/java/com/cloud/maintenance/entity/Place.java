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
 * @ClassName Place
 * @Description: TODO
 * @Author kevins
 * @Date 2019/11/5
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("place")
public class Place extends Model<Place> implements Serializable {

    private static final long serialVersionUID = 6668770202735113069L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /*
    竞赛名
     */
    private String name;
    /*
   描述
    */
    @TableField("`describe`")
    private String describe;
    /*
    详细地址
     */
    private String detail_address;
    /*
    图片
     */
    private String image;
    /*
    操作人
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
