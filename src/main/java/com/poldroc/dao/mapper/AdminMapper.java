package com.poldroc.dao.mapper;

import com.poldroc.dao.entity.AdminDO;
import com.poldroc.utils.JDBCUtil;

import java.util.List;

/**
 * Admin Mapper
 * @author Poldroc
 * @date 2024/6/15
 */

public class AdminMapper {

    public static AdminDO getAdminByUsername(String username) {
        String sql = "SELECT * FROM t_admin WHERE username = ?";
        List<AdminDO> adminList = JDBCUtil.executeQuery2Model(AdminDO.class, sql, username);
        if (adminList.isEmpty()) {
            return null;
        } else {
            return adminList.get(0);
        }
    }
}
