package com.poldroc.dao.entity;
/**
 * 图书实体类
 * @author Poldroc
 * @date 2024/6/14
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CREATE TABLE `t_books`  (
 *   `isbn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '国际标准书号，唯一标识一本书',
 *   `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
 *   `authors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
 *   `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
 *   `edition_number` int NULL DEFAULT NULL COMMENT '版次',
 *   `publication_date` datetime NULL DEFAULT NULL COMMENT '出版日期',
 *   `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍类型',
 *   PRIMARY KEY (`isbn`) USING BTREE,
 *   INDEX `idx_title`(`title` ASC) USING BTREE,
 *   INDEX `idx_authors`(`authors` ASC) USING BTREE
 * ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书籍信息表' ROW_FORMAT = Dynamic;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDO {

    private String isbn;

    private String title;

    private String authors;

    private String publisher;

    private Integer editionNumber;

    private Date publicationDate;

    private String type;


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "BookDO{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", editionNumber=" + editionNumber +
                ", publicationDate=" + (publicationDate != null ? dateFormat.format(publicationDate) : null) +
                ", type='" + type + '\'' +
                '}';
    }
}
