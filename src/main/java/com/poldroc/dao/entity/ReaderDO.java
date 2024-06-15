package com.poldroc.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 读者实体类
 * @author Poldroc
 * @date 2024/6/14
 */

/**
 * CREATE TABLE `t_reader`  (
 *   `reader_id` int NOT NULL AUTO_INCREMENT COMMENT '读者ID，唯一标识一个读者',
 *   `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '读者名',
 *   `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '读者姓',
 *   `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '读者地址',
 *   `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '读者电话号码',
 *   `limits` int NULL DEFAULT NULL COMMENT '借阅限额',
 *   PRIMARY KEY (`reader_id`) USING BTREE,
 *   INDEX `idx_reader_last_name`(`last_name` ASC) USING BTREE
 * ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '读者信息表' ROW_FORMAT = Dynamic;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReaderDO {

    private Integer readerId;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private Integer limits;
}
