package com.poldroc.service;

import com.alibaba.fastjson2.JSON;
import com.poldroc.common.UserContext;
import com.poldroc.dao.entity.AdminDO;
import com.poldroc.dao.mapper.AdminMapper;

/**
 * admin service
 *
 * @author Poldroc
 * @date 2024/6/14
 */

public class AdminService {


    /**
     * login
     */
    public static boolean login(AdminDO admin) {
        AdminDO adminDO = AdminMapper.getAdminByUsername(admin.getUsername());
        if (adminDO == null) {
            return false;
        }
        if (!adminDO.getPassword().equals(admin.getPassword())) {
            return false;
        }
        UserContext.setUser(JSON.toJSONString(adminDO));
        return true;
    }
}
