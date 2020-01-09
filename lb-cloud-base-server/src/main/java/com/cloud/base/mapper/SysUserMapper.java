package com.cloud.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.dto.UserDTO;
import com.cloud.base.entity.SysUser;
import com.cloud.base.mapper.sqlprovider.SqlProvider;
import com.cloud.core.mybatis.DataScope;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

//    @Insert("insert into sys_user (username,password,dept_id,job_id,phone,email,avatar,lock_flag) values (#{username},#{password},#{deptId},#{jobId},#{phone},#{email},#{avatar},#{lockFlag})")
//    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
//    boolean insertUser(SysUser sysUser);

    /**
     * 分页查询用户信息（含角色）
     *
     * @param page      分页
     * @param userDTO   查询参数
     * @param dataScope
     * @return list
     */
    @SelectProvider(type = SqlProvider.class, method = "queryUserVosPage")
    IPage<SysUser> getUserVosPage(Page page, @Param("query") UserDTO userDTO, DataScope dataScope);

    /**
     * @Author kevins
     * @Description 查询用户信息（含角色）
     * @Date 4:53 下午 2019/11/1
     * @Param [userDTO, dataScope]
     **/
    @SelectProvider(type = SqlProvider.class, method = "queryUserVosPage")
    List<SysUser> getUserVos(@Param("query") UserDTO userDTO, DataScope dataScope);


    @Select("SELECT su.user_id,su.`password`,su.username FROM sys_user su LEFT JOIN sys_user_social sus ON su.user_id = sus.user_id WHERE sus.provider_id = #{providerId} AND sus.provider_user_id = #{providerUserId}")
    SysUser getUserBySocial(@Param("providerId") String providerId, @Param("providerUserId") int providerUserId);

}
