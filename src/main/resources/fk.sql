/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731 (5.7.31-log)
 Source Host           : localhost:3306
 Source Schema         : fk

 Target Server Type    : MySQL
 Target Server Version : 50731 (5.7.31-log)
 File Encoding         : 65001

 Date: 01/01/2025 17:06:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a
-- ----------------------------
DROP TABLE IF EXISTS `a`;
CREATE TABLE `a`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of a
-- ----------------------------
INSERT INTO `a` VALUES (1, '小明');

-- ----------------------------
-- Table structure for b
-- ----------------------------
DROP TABLE IF EXISTS `b`;
CREATE TABLE `b`  (
  `id` int(11) NOT NULL,
  `a_id` int(11) NULL DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b
-- ----------------------------
INSERT INTO `b` VALUES (1, 1, '书1');
INSERT INTO `b` VALUES (2, 1, '书2');

-- ----------------------------
-- Table structure for c
-- ----------------------------
DROP TABLE IF EXISTS `c`;
CREATE TABLE `c`  (
  `id` int(11) NOT NULL,
  `a_id` int(11) NULL DEFAULT NULL,
  `teach_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c
-- ----------------------------
INSERT INTO `c` VALUES (1, 1, '老1');
INSERT INTO `c` VALUES (2, 1, '老2');

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_time` datetime NULL DEFAULT NULL COMMENT '请求时间',
  `code` int(255) NULL DEFAULT NULL COMMENT '接口返回状态码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES ('5434d9c2-9861-4212-9e68-580a066591a6', 'com.bettem.controller.PlanTemplateController.getPage', '127.0.0.1', '2022-10-17 15:00:00', 200);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `describes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户描述',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `born_date` date NULL DEFAULT NULL COMMENT '出生年月',
  `enter_school_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入学时间',
  `no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('18d4d8b08bb559ce8f3d904dca399764', '孙尚香', 2, 20, '上海闵行', 'sheet1用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('1c448c1276f43604eb57ef5863216815', '马可波罗', 1, 30, '江苏苏州', 'sheet2用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('269471af2ad2d283c2ba3081fcffe676', '黄忠', 1, 60, '浙江嘉兴', '用户批量导入', NULL, '2022-10-13 15:21:21', NULL, NULL, '2022-01-17', '20000520', NULL, NULL);
INSERT INTO `user` VALUES ('3346f42b4bd13d711a157929b6133d95', '孙尚香', 2, 20, '上海闵行', '用户批量导入', NULL, '2022-10-13 15:22:34', NULL, NULL, '2022-02-17', '20010817', NULL, NULL);
INSERT INTO `user` VALUES ('3e7ab22e975e689bb58b222209b3159d', '后羿', 1, 23, '江西南昌', '用户批量导入', NULL, '2022-10-13 15:21:21', NULL, NULL, '2022-03-17', '20020915', NULL, NULL);
INSERT INTO `user` VALUES ('419c4df639a494bdd795da4b172aec22', '黄忠', 1, 60, '浙江嘉兴', 'sheet2用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('570238fe339d7d2d809a311ed9749892', '黄忠', 1, 60, '浙江嘉兴', '用户批量导入', NULL, '2022-10-13 15:22:34', NULL, NULL, '2022-04-17', '20031030', NULL, NULL);
INSERT INTO `user` VALUES ('7cefbfb10af5ce5cc5e4b9a34b35a9a4', '黄忠', 1, 60, '浙江嘉兴', 'sheet1用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('8189876b53a818136867999863daea71', '马可波罗', 1, 30, '江苏苏州', '用户批量导入', NULL, '2022-10-13 15:22:34', NULL, NULL, '2022-05-17', '20020412', NULL, NULL);
INSERT INTO `user` VALUES ('96cd79aa8a4aa5db1952a63f42d81678', '孙尚香', 2, 20, '上海闵行', '用户批量导入', NULL, '2022-10-13 15:21:21', NULL, NULL, '2022-06-17', '20010620', NULL, NULL);
INSERT INTO `user` VALUES ('9a302833f82bfab6a5a89a9737f9af74', '后羿', 1, 23, '江西南昌', 'sheet2用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('b4dff9a2afbc8d03d1a6fb832809911b', '马可波罗', 1, 30, '江苏苏州', '用户批量导入', NULL, '2022-10-13 15:21:21', NULL, NULL, '2022-07-17', '20040825', NULL, NULL);
INSERT INTO `user` VALUES ('c7c60162f599f2c7a44a803a09522012', '孙尚香', 2, 20, '上海闵行', 'sheet2用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('d198a72ded7951fdc3e3cb09151eaa19', '马可波罗', 1, 30, '江苏苏州', 'sheet1用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('e33e83b8b36e2edbae6ae15b016191ca', '后羿', 1, 23, '江西南昌', 'sheet1用户批量导入', NULL, '2022-10-17 11:31:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('ed7bb66dbf5635e2e5f7f6a4ce108fa2', '后羿', 1, 23, '江西南昌', '用户批量导入', NULL, '2022-10-13 15:22:34', NULL, NULL, '2022-08-17', '20001226', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
