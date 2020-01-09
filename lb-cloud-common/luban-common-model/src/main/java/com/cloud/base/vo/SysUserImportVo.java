package com.cloud.base.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cloud.base.entity.SysRole;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName SysUserImportVo
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/17
 * @Version V1.0
 **/
@Data
public class SysUserImportVo implements Serializable {
    private static final long serialVersionUID = 4564499361454340884L;
    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 岗位ID
     */
    private Integer jobId;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "15")
    private String email;

    /**
     * 手机号
     */
    @Excel(name = "手机号", orderNum = "16")
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-锁定
     */
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    @Excel(name = "姓名", orderNum = "2")
    @NotNull
    @Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}", message = "姓名中文2-5位")
    private String name;//姓名
    @Excel(name = "警衔", orderNum = "3")
    @NotNull
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String policeRank;//警衔
    @Excel(name = "人员类型", orderNum = "4")
    @NotNull
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String type;//人员类型
    @Excel(name = "政治面貌", orderNum = "5")
    @NotNull
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String politicalOutlook;//政治面貌
    @Excel(name = "民族", orderNum = "6")
    @NotNull
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String nation;//民族
    @Excel(name = "出生日期", orderNum = "7")
    private String birthday;
    @Excel(name = "证件号码", orderNum = "8")
    @NotNull
    private String identificationNumber;//证件号码
    @Excel(name = "身高(cm)", orderNum = "9")
    @NotNull
    @Range(max = 250, min = 50, message = "身高范围应该在50-250内。")
    private Integer height;//身高
    @Excel(name = "体重(kg)", orderNum = "10")
    @NotNull
    @Range(max = 200, min = 10, message = "体重范围应该在10-200内。")
    private Integer weight;//体重
    @Excel(name = "血型", orderNum = "11")
    @NotNull
    @Pattern(regexp = "[A-Z]{1,2}", message = "必须1-2位字母")
    private String blood;//血型
    @Excel(name = "籍贯", orderNum = "12")
    @NotNull
    @Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
    private String nativePlace;//籍贯
    @Excel(name = "性别", orderNum = "13")
    @Pattern(regexp = "[\u4E00-\u9FA5]{1}", message = "不是中文")
    private String sex;//性别
    @Excel(name = "身份证", orderNum = "17")
    private String id_card;//身份证
    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList;
    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    @Excel(name = "单位", orderNum = "1")
    private String deptName;
    ;
    /**
     * 非数据库字段
     * 岗位名称
     */
    @TableField(exist = false)
    @NotNull
    @Excel(name = "岗位", orderNum = "14")
    private String jobName;
}
