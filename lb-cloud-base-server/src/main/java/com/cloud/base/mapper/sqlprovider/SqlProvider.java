package com.cloud.base.mapper.sqlprovider;

import com.cloud.base.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SqlProvider
 * @Description
 * @Author kevins
 * @Version v1.0
 * @Date 2019-07-14 12:48 PM
 **/
@Slf4j
public class SqlProvider {

    public String queryUserVosPage(@Param("query") UserDTO userDTO) {
        StringBuilder sb = new StringBuilder();
        StringBuilder deptSb = new StringBuilder();
        sb.append("SELECT\n" +
                "        `user`.user_id,\n" +
                "        `user`.username,\n" +
                "        `user`.phone,\n" +
                "        `user`.email,\n" +
                "        `user`.avatar,\n" +
                "        `user`.dept_id,\n" +
                "        `user`.name,\n" +
                "        `user`.police_rank,\n" +
                "        `user`.type,\n" +
                "        `user`.political_outlook,\n" +
                "        `user`.nation,\n" +
                "        `user`.birthday,\n" +
                "        `user`.identification_number,\n" +
                "        `user`.height,\n" +
                "        `user`.weight,\n" +
                "        `user`.blood,\n" +
                "        `user`.native_place,\n" +
                "        `user`.sex,\n" +
                "        `user`.id_card,\n" +
                "        `user`.create_time,\n" +
                "        `user`.update_time,\n" +
                "        `user`.del_flag,\n" +
                "        `user`.lock_flag,\n" +
                "        d.name AS deptName,\n" +
                "        j.id AS job_id,\n" +
                "        j.job_name as jobName\n" +
                "        FROM\n" +
                "        sys_user AS `user`\n" +
                "        LEFT JOIN sys_dept AS d ON d.dept_id = `user`.dept_id\n" +
                "        LEFT JOIN sys_job AS j ON j.id = `user`.job_id where");
//        if (userDTO != null && userDTO.getUsername() != null) {
//            sb.append("  `user`.username LIKE CONCAT('%',#{query.username},'%') and");
//
//        }
        if (userDTO != null && userDTO.getDeptId() != null) {
            sb.append(" `user`.dept_id = #{query.deptId} ");

        } else {
            for (Integer dept : userDTO.getDeptIdsList()) {
                deptSb.append(dept).append(",");
            }
            String dept = deptSb.substring(0, deptSb.length() - 1);
            sb.append(" `user`.dept_id in (" + dept + ") ");
        }

        if (userDTO != null && userDTO.getType() != null) {
            sb.append(" and `user`.type = #{query.type} ");

        }
        sb.append(" ORDER BY `user`.create_time DESC");
        if (log.isInfoEnabled()) {
            log.info(sb.toString());
        }
        return sb.toString();
    }
}
