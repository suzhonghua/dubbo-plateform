/*
 Navicat Premium Data Transfer

 Source Server         : 39.101.204.201
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 39.101.204.201:3306
 Source Schema         : upms

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 17/05/2020 16:44:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '总经办', 0, '2020-03-13 13:13:16', '2020-03-13 13:14:31', '0', 0);
INSERT INTO `sys_dept` VALUES (2, '行政中心', 1, '2020-03-13 13:13:30', NULL, '0', 1);
INSERT INTO `sys_dept` VALUES (3, '技术中心', 2, '2020-03-13 13:14:55', NULL, '0', 1);
INSERT INTO `sys_dept` VALUES (4, '运营中心', 3, '2020-03-13 13:15:15', NULL, '0', 1);
INSERT INTO `sys_dept` VALUES (5, '研发中心', 5, '2020-03-13 13:15:34', NULL, '0', 3);
INSERT INTO `sys_dept` VALUES (6, '产品中心', 6, '2020-03-13 13:15:49', NULL, '0', 3);
INSERT INTO `sys_dept` VALUES (7, '测试中心', 7, '2020-03-13 13:16:02', NULL, '0', 3);
INSERT INTO `sys_dept` VALUES (8, '运营', 2, '2020-05-09 13:32:35', '2020-05-09 13:34:57', '1', 4);

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
  INDEX `idx1`(`ancestor`) USING BTREE,
  INDEX `idx2`(`descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 2);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (1, 6);
INSERT INTO `sys_dept_relation` VALUES (1, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (3, 6);
INSERT INTO `sys_dept_relation` VALUES (3, 7);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (6, 6);
INSERT INTO `sys_dept_relation` VALUES (7, 7);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0');
INSERT INTO `sys_dict` VALUES (2, 'log_type', '日志类型', '2020-03-13 14:21:01', '2020-03-13 14:21:01', '0-正常 1 异常', '1', '0');
INSERT INTO `sys_dict` VALUES (4, 'test', 'test', '2020-05-10 17:39:26', '2020-05-11 01:58:44', '111', '0', '1');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` int(11) NOT NULL,
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`label`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0');
INSERT INTO `sys_dict_item` VALUES (2, 1, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0');
INSERT INTO `sys_dict_item` VALUES (3, 2, '0', '正常', 'log_type', '正常', 0, '2020-03-13 14:23:22', '2020-03-13 14:23:22', '正常', '0');
INSERT INTO `sys_dict_item` VALUES (4, 2, '9', '异常', 'log_type', '异常', 1, '2020-03-13 14:23:35', '2020-03-13 14:23:35', '异常', '0');
INSERT INTO `sys_dict_item` VALUES (5, 4, 'teste', 'test', 'test', 'test', 1, '2020-05-11 01:43:20', '2020-05-11 01:55:54', 'ttttt', '1');
INSERT INTO `sys_dict_item` VALUES (6, 4, '11', '11', '11', '11', 2, '2020-05-11 01:55:03', '2020-05-11 01:55:09', '1111', '1');
INSERT INTO `sys_dict_item` VALUES (7, 4, '11', '1', '11', '11', 1, '2020-05-10 09:56:10', '2020-05-11 01:58:35', '222221', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '0', '新增菜单', 'admin', 'admin', '2020-05-09 05:21:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '117', '0', NULL);
INSERT INTO `sys_log` VALUES (2, '0', '新增菜单', 'admin', 'admin', '2020-05-09 05:23:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '119', '0', NULL);
INSERT INTO `sys_log` VALUES (3, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:27:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3400', 'DELETE', '', '514', '0', NULL);
INSERT INTO `sys_log` VALUES (4, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3301', 'DELETE', '', '328', '0', NULL);
INSERT INTO `sys_log` VALUES (5, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3100', 'DELETE', '', '277', '0', NULL);
INSERT INTO `sys_log` VALUES (6, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3200', 'DELETE', '', '347', '0', NULL);
INSERT INTO `sys_log` VALUES (7, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3302', 'DELETE', '', '294', '0', NULL);
INSERT INTO `sys_log` VALUES (8, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3303', 'DELETE', '', '363', '0', NULL);
INSERT INTO `sys_log` VALUES (9, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3300', 'DELETE', '', '381', '0', NULL);
INSERT INTO `sys_log` VALUES (10, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3000', 'DELETE', '', '302', '0', NULL);
INSERT INTO `sys_log` VALUES (11, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:31:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/2301', 'DELETE', '', '347', '0', NULL);
INSERT INTO `sys_log` VALUES (12, '0', '删除菜单', 'admin', 'admin', '2020-05-09 05:32:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/2300', 'DELETE', '', '361', '0', NULL);
INSERT INTO `sys_log` VALUES (13, '0', '修改角色', 'admin', 'admin', '2020-05-09 10:30:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '3191', '0', NULL);
INSERT INTO `sys_log` VALUES (14, '0', '新增菜单', 'admin', 'admin', '2020-05-09 10:40:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '99', '0', NULL);
INSERT INTO `sys_log` VALUES (15, '0', '修改角色', 'admin', 'admin', '2020-05-09 10:48:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '312', '0', NULL);
INSERT INTO `sys_log` VALUES (16, '0', '添加角色', 'admin', 'admin', '2020-05-09 10:54:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/add', 'POST', '', '220', '0', NULL);
INSERT INTO `sys_log` VALUES (17, '0', '删除角色', 'admin', 'admin', '2020-05-09 10:55:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/delete/2', 'DELETE', '', '277', '0', NULL);
INSERT INTO `sys_log` VALUES (18, '0', '删除菜单', 'admin', 'admin', '2020-05-09 10:56:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/1304', 'DELETE', '', '329', '0', NULL);
INSERT INTO `sys_log` VALUES (19, '0', '新增菜单', 'admin', 'admin', '2020-05-09 13:09:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '217', '0', NULL);
INSERT INTO `sys_log` VALUES (20, '0', '修改角色', 'admin', 'admin', '2020-05-09 13:09:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '521', '0', NULL);
INSERT INTO `sys_log` VALUES (21, '0', '添加部门', 'admin', 'admin', '2020-05-09 13:32:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dept/add', 'POST', '', '315', '0', NULL);
INSERT INTO `sys_log` VALUES (22, '0', '删除部门', 'admin', 'admin', '2020-05-09 13:34:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dept/delete/8', 'DELETE', '', '219', '0', NULL);
INSERT INTO `sys_log` VALUES (23, '0', '添加用户', 'admin', 'admin', '2020-05-09 14:41:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/add', 'POST', '', '54659', '0', NULL);
INSERT INTO `sys_log` VALUES (24, '0', '更新用户信息', 'admin', 'admin', '2020-05-10 02:54:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/update', 'PUT', '', '287', '0', NULL);
INSERT INTO `sys_log` VALUES (25, '0', '更新用户信息', 'admin', 'admin', '2020-05-10 02:54:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/update', 'PUT', '', '333', '0', NULL);
INSERT INTO `sys_log` VALUES (26, '0', '更新用户信息', 'admin', 'admin', '2020-05-10 02:55:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/update', 'PUT', '', '352', '0', NULL);
INSERT INTO `sys_log` VALUES (27, '0', '添加用户', 'admin', 'admin', '2020-05-10 02:57:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/add', 'POST', '', '362', '0', NULL);
INSERT INTO `sys_log` VALUES (28, '0', '更新用户信息', 'admin', 'admin', '2020-05-10 02:57:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/update', 'PUT', '', '706', '0', NULL);
INSERT INTO `sys_log` VALUES (29, '0', '新增菜单', 'admin', 'admin', '2020-05-10 03:32:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '103', '0', NULL);
INSERT INTO `sys_log` VALUES (30, '0', '修改角色', 'admin', 'admin', '2020-05-10 03:33:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '350', '0', NULL);
INSERT INTO `sys_log` VALUES (31, '0', '添加字典', 'admin', 'admin', '2020-05-10 03:51:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/add', 'POST', '', '79', '0', NULL);
INSERT INTO `sys_log` VALUES (32, '0', '添加字典', 'admin', 'admin', '2020-05-11 01:39:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/add', 'POST', '', '122', '0', NULL);
INSERT INTO `sys_log` VALUES (33, '0', '新增字典项', 'admin', 'admin', '2020-05-11 01:43:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/add', 'POST', '', '89', '0', NULL);
INSERT INTO `sys_log` VALUES (34, '0', '新增字典项', 'admin', 'admin', '2020-05-11 01:55:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/add', 'POST', '', '83', '0', NULL);
INSERT INTO `sys_log` VALUES (35, '0', '删除字典项', 'admin', 'admin', '2020-05-11 01:55:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/delete/6', 'DELETE', '', '204', '0', NULL);
INSERT INTO `sys_log` VALUES (36, '0', '删除字典项', 'admin', 'admin', '2020-05-11 01:55:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/delete/5', 'DELETE', '', '175', '0', NULL);
INSERT INTO `sys_log` VALUES (37, '0', '新增字典项', 'admin', 'admin', '2020-05-11 01:56:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/add', 'POST', '', '86', '0', NULL);
INSERT INTO `sys_log` VALUES (38, '0', '修改字典项', 'admin', 'admin', '2020-05-11 01:56:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/update', 'PUT', '', '150', '0', NULL);
INSERT INTO `sys_log` VALUES (39, '0', '修改字典项', 'admin', 'admin', '2020-05-11 01:56:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/update', 'PUT', '', '5165', '0', NULL);
INSERT INTO `sys_log` VALUES (40, '0', '修改字典', 'admin', 'admin', '2020-05-11 01:57:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/update', 'PUT', '', '149', '0', NULL);
INSERT INTO `sys_log` VALUES (41, '0', '修改字典项', 'admin', 'admin', '2020-05-11 01:58:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/update', 'PUT', '', '7893', '0', NULL);
INSERT INTO `sys_log` VALUES (42, '0', '删除字典项', 'admin', 'admin', '2020-05-11 01:58:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/item/delete/7', 'DELETE', '', '207', '0', NULL);
INSERT INTO `sys_log` VALUES (43, '0', '删除字典', 'admin', 'admin', '2020-05-11 01:58:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/dict/delete/4', 'DELETE', '', '5364', '0', NULL);
INSERT INTO `sys_log` VALUES (44, '0', '新增菜单', 'admin', 'admin', '2020-05-11 13:54:43', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '97', '0', NULL);
INSERT INTO `sys_log` VALUES (45, '0', '新增菜单', 'admin', 'admin', '2020-05-11 13:57:30', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '85', '0', NULL);
INSERT INTO `sys_log` VALUES (46, '0', '新增菜单', 'admin', 'admin', '2020-05-11 13:59:17', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '82', '0', NULL);
INSERT INTO `sys_log` VALUES (47, '0', '更新菜单', 'admin', 'admin', '2020-05-11 13:59:36', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '166', '0', NULL);
INSERT INTO `sys_log` VALUES (48, '0', '更新菜单', 'admin', 'admin', '2020-05-11 14:01:20', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '121', '0', NULL);
INSERT INTO `sys_log` VALUES (49, '0', '更新菜单', 'admin', 'admin', '2020-05-11 14:01:27', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '113', '0', NULL);
INSERT INTO `sys_log` VALUES (50, '0', '新增菜单', 'admin', 'admin', '2020-05-11 14:03:12', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '87', '0', NULL);
INSERT INTO `sys_log` VALUES (51, '0', '新增菜单', 'admin', 'admin', '2020-05-11 14:04:42', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/add', 'POST', '', '85', '0', NULL);
INSERT INTO `sys_log` VALUES (52, '0', '修改角色', 'admin', 'admin', '2020-05-11 14:05:28', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '330', '0', NULL);
INSERT INTO `sys_log` VALUES (53, '0', '更新菜单', 'admin', 'admin', '2020-05-11 16:11:35', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '142', '0', NULL);
INSERT INTO `sys_log` VALUES (54, '0', '更新菜单', 'admin', 'admin', '2020-05-11 16:11:47', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '115', '0', NULL);
INSERT INTO `sys_log` VALUES (55, '0', '更新菜单', 'admin', 'admin', '2020-05-11 16:11:57', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '126', '0', NULL);
INSERT INTO `sys_log` VALUES (56, '0', '更新菜单', 'admin', 'admin', '2020-05-11 16:12:05', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/update', 'PUT', '', '113', '0', NULL);
INSERT INTO `sys_log` VALUES (57, '0', '修改角色', 'admin', 'admin', '2020-05-11 16:25:39', NULL, '192.168.31.84', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/role/update', 'PUT', '', '261', '0', NULL);
INSERT INTO `sys_log` VALUES (58, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:39:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3419', 'DELETE', '', '761', '0', NULL);
INSERT INTO `sys_log` VALUES (59, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3418', 'DELETE', '', '237', '0', NULL);
INSERT INTO `sys_log` VALUES (60, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3417', 'DELETE', '', '276', '0', NULL);
INSERT INTO `sys_log` VALUES (61, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3416', 'DELETE', '', '238', '0', NULL);
INSERT INTO `sys_log` VALUES (62, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3415', 'DELETE', '', '236', '0', NULL);
INSERT INTO `sys_log` VALUES (63, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3410', 'DELETE', '', '237', '0', NULL);
INSERT INTO `sys_log` VALUES (64, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3409', 'DELETE', '', '240', '0', NULL);
INSERT INTO `sys_log` VALUES (65, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3420', 'DELETE', '', '245', '0', NULL);
INSERT INTO `sys_log` VALUES (66, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3414', 'DELETE', '', '237', '0', NULL);
INSERT INTO `sys_log` VALUES (67, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3413', 'DELETE', '', '237', '0', NULL);
INSERT INTO `sys_log` VALUES (68, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:40:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3411', 'DELETE', '', '233', '0', NULL);
INSERT INTO `sys_log` VALUES (69, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3412', 'DELETE', '', '238', '0', NULL);
INSERT INTO `sys_log` VALUES (70, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3407', 'DELETE', '', '232', '0', NULL);
INSERT INTO `sys_log` VALUES (71, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3421', 'DELETE', '', '240', '0', NULL);
INSERT INTO `sys_log` VALUES (72, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3422', 'DELETE', '', '233', '0', NULL);
INSERT INTO `sys_log` VALUES (73, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3423', 'DELETE', '', '241', '0', NULL);
INSERT INTO `sys_log` VALUES (74, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3424', 'DELETE', '', '237', '0', NULL);
INSERT INTO `sys_log` VALUES (75, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3425', 'DELETE', '', '235', '0', NULL);
INSERT INTO `sys_log` VALUES (76, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3408', 'DELETE', '', '233', '0', NULL);
INSERT INTO `sys_log` VALUES (77, '0', '删除菜单', 'admin', 'admin', '2020-05-17 08:41:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/menu/delete/3406', 'DELETE', '', '265', '0', NULL);
INSERT INTO `sys_log` VALUES (78, '0', '更新用户信息', 'admin', 'admin', '2020-05-17 08:43:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36', '/user/update', 'PUT', '', '509', '0', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序值',
  `keep_alive` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '1-开启，0- 关闭',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3426 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1100, '用户管理', NULL, '/admin/user/index', 2000, 'icon-yonghuguanli', NULL, 1, '0', '0', '2017-11-02 22:24:37', '2020-05-08 15:15:06', '0');
INSERT INTO `sys_menu` VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, NULL, 1, '0', '1', '2017-11-08 09:52:09', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, NULL, 1, '0', '1', '2017-11-08 09:52:48', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, NULL, 1, '0', '1', '2017-11-08 09:54:01', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1200, '菜单管理', NULL, '/admin/menu/index', 2000, 'icon-caidanguanli', NULL, 4, '0', '0', '2017-11-08 01:57:27', '2020-05-08 07:15:06', '0');
INSERT INTO `sys_menu` VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, NULL, 1, '0', '1', '2017-11-08 10:15:53', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, NULL, 1, '0', '1', '2017-11-08 10:16:23', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, NULL, 1, '0', '1', '2017-11-08 10:16:43', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1300, '角色管理', NULL, '/admin/role/index', 2000, 'icon-jiaoseguanli', NULL, 3, '0', '0', '2017-11-08 10:13:37', '2020-05-08 15:15:06', '0');
INSERT INTO `sys_menu` VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, NULL, 1, '0', '1', '2017-11-08 10:14:18', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, NULL, 1, '0', '1', '2017-11-08 10:14:41', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, NULL, 1, '0', '1', '2017-11-08 10:14:59', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1400, '部门管理', NULL, '/admin/dept/index', 2000, 'icon-web-icon-', NULL, 2, '0', '0', '2018-01-20 05:17:19', '2020-05-08 07:15:06', '0');
INSERT INTO `sys_menu` VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, NULL, 1, '0', '1', '2018-01-20 14:56:16', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, NULL, 1, '0', '1', '2018-01-20 14:56:59', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, NULL, 1, '0', '1', '2018-01-20 14:57:28', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (2000, '系统管理', NULL, '#', -1, 'icon-xitongguanli', NULL, 99, '1', '0', '2017-11-07 12:56:00', '2020-05-08 07:15:06', '0');
INSERT INTO `sys_menu` VALUES (2100, '日志管理', NULL, '/admin/log/index', 2000, 'icon-rizhiguanli', NULL, 6, '0', '0', '2017-11-20 06:06:22', '2020-05-08 07:15:06', '0');
INSERT INTO `sys_menu` VALUES (2101, '日志删除', 'sys_log_del', NULL, 2100, NULL, NULL, 1, '0', '1', '2017-11-20 20:37:37', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (2200, '字典管理', NULL, '/admin/dict/index', 2000, 'icon-navicon-zdgl', NULL, 5, '0', '0', '2017-11-29 03:30:52', '2020-05-08 07:15:06', '0');
INSERT INTO `sys_menu` VALUES (2201, '字典删除', 'sys_dict_del', NULL, 2200, NULL, NULL, 1, '0', '1', '2017-11-29 11:30:11', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (2202, '字典新增', 'sys_dict_add', NULL, 2200, NULL, NULL, 1, '0', '1', '2018-05-11 22:34:55', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (2203, '字典修改', 'sys_dict_edit', NULL, 2200, NULL, NULL, 1, '0', '1', '2018-05-11 22:36:03', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (2500, '服务监控', NULL, 'http://192.168.31.188:9700', 2000, 'icon-server', NULL, 10, '0', '0', '2018-06-26 10:50:32', '2020-05-10 12:43:19', '0');
INSERT INTO `sys_menu` VALUES (3401, '用户查看', 'sys_user_view', NULL, 1100, NULL, NULL, 1, '0', '1', '2020-05-09 05:21:46', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (3402, '菜单查看', 'sys_menu_view', NULL, 1200, NULL, NULL, 1, '0', '1', '2020-05-09 05:23:57', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (3403, '查看角色', 'sys_role_view', NULL, 1300, NULL, NULL, 1, '0', '1', '2020-05-09 10:40:47', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (3404, '查看部门', 'sys_dept_view', NULL, 1400, NULL, NULL, 1, '0', '1', '2020-05-09 13:09:42', '2020-05-15 13:29:29', '0');
INSERT INTO `sys_menu` VALUES (3405, '查看字典', 'sys_dict_view', NULL, 2200, NULL, NULL, 1, '0', '1', '2020-05-10 03:32:17', '2020-05-15 13:29:29', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_idx1_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '管理员', '2017-10-27 07:45:51', '2018-12-24 06:09:11', '0');
INSERT INTO `sys_role` VALUES (2, 'test', 'test', 'test', '2020-05-09 10:54:36', '2020-05-09 10:55:38', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2500);
INSERT INTO `sys_role_menu` VALUES (1, 3401);
INSERT INTO `sys_role_menu` VALUES (1, 3402);
INSERT INTO `sys_role_menu` VALUES (1, 3403);
INSERT INTO `sys_role_menu` VALUES (1, 3404);
INSERT INTO `sys_role_menu` VALUES (1, 3405);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0-正常，9-锁定',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0-正常，1-删除',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_idx1_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$CjM1QiH.dUPQKfSL1ovGNufS6kJ.fzCLMn/X6L1KbKV.3EfG9xf02', NULL, '17034642999', 'https://secure.gravatar.com/avatar/767fc9c115a1b989744c755db47feb60', 1, '2018-04-19 07:15:18', '2020-05-17 02:04:44', '0', '0');
INSERT INTO `sys_user` VALUES (3, 'test', '', NULL, '15554186555', 'https://secure.gravatar.com/avatar/767fc9c115a1b989744c755db47feb60', 1, '2020-05-08 22:41:35', '2020-05-17 16:43:41', '0', '0');
INSERT INTO `sys_user` VALUES (4, 'test1', '$2a$10$YJYvidaeYiGV7cJzq/aQm.QU79K/Hsb./pK5Dc6R1LE/Xqtzad./S', NULL, '15555555555', 'https://secure.gravatar.com/avatar/767fc9c115a1b989744c755db47feb60', 4, '2020-05-10 02:57:15', '2020-05-17 02:04:47', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (3, 1);
INSERT INTO `sys_user_role` VALUES (4, 1);

SET FOREIGN_KEY_CHECKS = 1;
