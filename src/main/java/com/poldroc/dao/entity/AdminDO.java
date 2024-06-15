package com.poldroc.dao.entity;
/**
 * 管理员实体类
 * @author Poldroc
 * @date 2024/6/14
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CREATE TABLE `t_admin`  (
 *   `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
 *   `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名',
 *   `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员密码',
 *   PRIMARY KEY (`id`) USING BTREE,
 *   UNIQUE INDEX `username`(`username` ASC) USING BTREE
 * ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDO {

    private Integer id;

    private String username;

    private String password;

}
