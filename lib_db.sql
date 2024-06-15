/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : lib_db

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 16/06/2024 01:02:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for t_books
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books`  (
  `isbn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '国际标准书号，唯一标识一本书',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
  `authors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `edition_number` int NULL DEFAULT NULL COMMENT '版次',
  `publication_date` datetime NULL DEFAULT NULL COMMENT '出版日期',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍类型',
  PRIMARY KEY (`isbn`) USING BTREE,
  INDEX `idx_title`(`title` ASC) USING BTREE,
  INDEX `idx_authors`(`authors` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书籍信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES ('9787030619716', '计算机组成原理', '白中英、戴志涛', '科学出版社', 6, '2019-08-01 00:00:00', '教材');
INSERT INTO `t_books` VALUES ('9787040396638', '高等数学·上册: 第七版', '同济大学数学系', '高等教育出版社', 7, '2014-01-01 00:00:00', '教材');
INSERT INTO `t_books` VALUES ('9787544711326', '小王子', '圣埃克苏佩里', '译林出版社', 1, '2010-01-01 00:00:00', '童话');

-- ----------------------------
-- Table structure for t_reader
-- ----------------------------
DROP TABLE IF EXISTS `t_reader`;
CREATE TABLE `t_reader`  (
  `reader_id` int NOT NULL AUTO_INCREMENT COMMENT '读者ID，唯一标识一个读者',
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '读者名',
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '读者姓',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '读者地址',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '读者电话号码',
  `limits` int NULL DEFAULT NULL COMMENT '借阅限额',
  PRIMARY KEY (`reader_id`) USING BTREE,
  INDEX `idx_reader_last_name`(`last_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '读者信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reader
-- ----------------------------
INSERT INTO `t_reader` VALUES (1, '三', '张', '福建省福州市福州大学', '180054700000000', 6);

-- ----------------------------
-- Table structure for t_records
-- ----------------------------
DROP TABLE IF EXISTS `t_records`;
CREATE TABLE `t_records`  (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '记录ID，唯一标识一条借阅记录',
  `isbn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国际标准书号，引用books表的isbn',
  `reader_id` int NULL DEFAULT NULL COMMENT '读者ID，引用reader表的reader_id',
  `borrowing_date` datetime NULL DEFAULT NULL COMMENT '借阅日期',
  `return_date` datetime NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `idx_record_isbn`(`isbn` ASC) USING BTREE,
  INDEX `idx_record_reader_id`(`reader_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_records
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
