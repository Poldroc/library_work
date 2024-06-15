package com.poldroc.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 借阅记录实体类
 * @author Poldroc
 * @date 2024/6/14
 */

/**
 * CREATE TABLE `t_record`  (
 *   `record_id` int NOT NULL AUTO_INCREMENT COMMENT '记录ID，唯一标识一条借阅记录',
 *   `isbn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国际标准书号，引用books表的isbn',
 *   `reader_id` int NULL DEFAULT NULL COMMENT '读者ID，引用reader表的reader_id',
 *   `borrowing_date` datetime NULL DEFAULT NULL COMMENT '借阅日期',
 *   `return_date` datetime NULL DEFAULT NULL COMMENT '归还日期',
 *   PRIMARY KEY (`record_id`) USING BTREE,
 *   INDEX `idx_record_isbn`(`isbn` ASC) USING BTREE,
 *   INDEX `idx_record_reader_id`(`reader_id` ASC) USING BTREE
 * ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDO {

    private Integer recordId;

    private String isbn;

    private Integer readerId;

    private Date borrowingDate;

    private Date returnDate;
}
