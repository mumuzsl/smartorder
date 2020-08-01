/*
 Navicat Premium Data Transfer

 Source Server         : c
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : smartorder

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 01/08/2020 13:09:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recommend` int(11) NULL DEFAULT 0,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `txt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dishes
-- ----------------------------
INSERT INTO `dishes` VALUES (1, '鱼香肉丝', 25.00, 'api/file/downloadImage/d1.jpg', 0, NULL, NULL);
INSERT INTO `dishes` VALUES (2, '青椒肉丝', 27.00, 'api/file/downloadImage/d2.jpg', 0, '', '');
INSERT INTO `dishes` VALUES (3, '番茄炒蛋', 30.00, 'api/file/downloadImage/d3.jpg', 1, NULL, NULL);
INSERT INTO `dishes` VALUES (4, '土豆丝', 25.00, 'api/file/downloadImage/ea35d687be4a48a0b2583aa33de8e861.jpg', 0, '', '');
INSERT INTO `dishes` VALUES (8, '红烧肉', 30.00, 'api/file/downloadImage/65a501d16caa42889a72ffa8381bd33d.jpg', 0, '', '');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uesr_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `uid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (21, '公告', NULL, 4, NULL, 159611101749658101);
INSERT INTO `notice` VALUES (22, '公告内容1', NULL, 1, NULL, 159621117946614101);
INSERT INTO `notice` VALUES (23, '公告内容2', NULL, 1, NULL, 159621167401963100);
INSERT INTO `notice` VALUES (24, '公告内容。。。。。。。', NULL, 1, NULL, 159625470352910100);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desk_num` int(11) NULL DEFAULT NULL,
  `uid` bigint(20) NOT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`, `uid`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, 0, '2020-07-25 20:11:30', '2020-07-25 10:52:20', 50.00, 1, 0);
INSERT INTO `order` VALUES (2, 1, 1, '2020-07-25 20:11:35', '2020-07-24 13:02:38', 70.00, 1, 0);
INSERT INTO `order` VALUES (4, 3, 159568121050837100, '2020-07-25 20:46:51', '2020-07-31 14:21:17', 135.00, 1, 0);
INSERT INTO `order` VALUES (5, NULL, 159612018586379100, '2020-07-30 22:43:06', '2020-07-31 14:18:35', NULL, 3, 1);
INSERT INTO `order` VALUES (11, 3, 159612331444214100, '2020-07-30 23:35:14', NULL, 0.00, 3, 1);
INSERT INTO `order` VALUES (13, 3, 159612344181522102, '2020-07-30 23:37:22', NULL, 0.00, 3, 1);
INSERT INTO `order` VALUES (14, 3, 159612372049654100, '2020-07-30 23:42:00', NULL, 0.00, 3, 1);
INSERT INTO `order` VALUES (15, 3, 159612405424219101, '2020-07-30 23:47:34', NULL, 0.00, 3, 1);
INSERT INTO `order` VALUES (16, 3, 159612408936673102, '2020-07-30 23:48:10', '2020-08-01 11:05:57', 0.00, 3, 1);
INSERT INTO `order` VALUES (17, 1, 159612435824192100, '2020-07-30 23:52:38', '2020-07-31 23:54:50', 0.00, 3, 1);
INSERT INTO `order` VALUES (18, NULL, 159612449227928101, '2020-07-30 23:54:52', '2020-07-31 20:38:46', 0.00, 3, 1);
INSERT INTO `order` VALUES (19, 1, 159617653369258100, '2020-07-31 14:22:14', '2020-08-01 11:41:01', 30.00, 3, 0);
INSERT INTO `order` VALUES (20, 1, 159621110415774100, '2020-07-31 23:58:24', '2020-08-01 11:57:44', 30.00, 3, 1);
INSERT INTO `order` VALUES (21, 1, 159625477293846101, '2020-08-01 12:06:13', NULL, 52.00, 3, 0);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `dishes_id` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `count` int(11) NOT NULL,
  `status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `dishes_id`(`dishes_id`) USING BTREE,
  CONSTRAINT `dishes_id` FOREIGN KEY (`dishes_id`) REFERENCES `dishes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, 1, 1, 20.00, 1, 1);
INSERT INTO `order_detail` VALUES (2, 1, 2, 20.00, 1, 0);
INSERT INTO `order_detail` VALUES (3, 1, 3, 25.00, 1, 0);
INSERT INTO `order_detail` VALUES (4, 2, 1, 25.00, 1, 0);
INSERT INTO `order_detail` VALUES (5, 2, 2, 25.00, 1, 0);
INSERT INTO `order_detail` VALUES (7, 4, 1, 25.00, 1, 1);
INSERT INTO `order_detail` VALUES (8, 4, 2, 25.00, 2, 0);
INSERT INTO `order_detail` VALUES (13, 1, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (14, 1, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (15, 1, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (16, 1, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (17, 17, 3, 30.00, 1, 1);
INSERT INTO `order_detail` VALUES (18, 18, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (19, 19, 3, 30.00, 1, 1);
INSERT INTO `order_detail` VALUES (20, 20, 3, 30.00, 1, 0);
INSERT INTO `order_detail` VALUES (21, 21, 1, 25.00, 1, 0);
INSERT INTO `order_detail` VALUES (22, 21, 2, 27.00, 1, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ADMIN');
INSERT INTO `role` VALUES (2, 'WAITER');
INSERT INTO `role` VALUES (3, 'CHEF');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT 1,
  `menu_id` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 1, 2);
INSERT INTO `role_menu` VALUES (3, 1, 3);
INSERT INTO `role_menu` VALUES (4, 1, 4);
INSERT INTO `role_menu` VALUES (5, 1, 5);
INSERT INTO `role_menu` VALUES (6, 1, 6);
INSERT INTO `role_menu` VALUES (7, 2, 7);
INSERT INTO `role_menu` VALUES (8, 2, 8);
INSERT INTO `role_menu` VALUES (9, 3, 9);
INSERT INTO `role_menu` VALUES (10, 1, 10);
INSERT INTO `role_menu` VALUES (11, 2, 10);
INSERT INTO `role_menu` VALUES (12, 3, 10);
INSERT INTO `role_menu` VALUES (13, 1, 11);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int(11) NULL DEFAULT 0 COMMENT '菜单排序',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_at` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, 10, '运营功能', 'fa fa-bars', '', '_self', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (2, 1, '顾客结账', 'fa fa-bars', '/page/customercheckout.html', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (3, 10, '餐厅管理', 'fa fa-bars', '', '_self', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (4, 3, '员工管理', 'fa fa-bars', '/page/user.html', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (5, 3, '菜品管理', 'fa fa-bars', '/page/food.html', '_self', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (6, 3, '经营数据', 'fa fa-bars', '/page/business-data.html', '_self', 3, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (7, 10, '点餐', 'fa fa-bars', 'order-food.html', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (8, 10, '结账', 'fa fa-bars', '/page/tablecheckout.html', '_self', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (9, 10, '备菜', 'fa fa-bars', '/page/kitchen.html', '_self', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (10, 0, '全部功能', 'fa fa-bars', '', '_self', 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (11, 3, '发布公告', 'fa fa-bars', '/page/notice.html', '_self', 4, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` int(11) NOT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE,
  INDEX `role_id`(`role`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `role_id` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$lfymUWc.oRAC/xBmhKFPaOUJFMGZmyBPxjywmiXE.OD4C/smXpAxa', 1, '');
INSERT INTO `user` VALUES (3, 'waiter', '$2a$10$5IN5ryvb4Y9FCcDNn63kyul5nDAiZqlEGIdDSkmf8e/WW0Qghgf9m', 2, '');
INSERT INTO `user` VALUES (4, 'chef', '$2a$10$5IN5ryvb4Y9FCcDNn63kyul5nDAiZqlEGIdDSkmf8e/WW0Qghgf9m', 3, '');
INSERT INTO `user` VALUES (5, 'chef1', '$2a$10$5IN5ryvb4Y9FCcDNn63kyul5nDAiZqlEGIdDSkmf8e/WW0Qghgf9m', 3, NULL);
INSERT INTO `user` VALUES (23, 'chef6', '$2a$10$76woR7peW7hMs9nE71Vw7eliN8TNF.s.JHlHjH8Ag8AiqLda/1NGi', 3, 'api/file/downloadImage/668e25e4752f4e9b9e8a2f4bf687a16f.png');
INSERT INTO `user` VALUES (31, 'sfs', '$2a$10$v134fI20ZHfkxbAX7zacFuB/rFv3NYHGE9/pqkHyLAHGFMRJbUaNS', 2, 'api/file/downloadImage/6a007c2d33eb4d7e835cfd2a888cca5c.png');
INSERT INTO `user` VALUES (32, 'mumu', '$2a$10$mbNeGOI.mUWInzOZ9DGaMOROREpR14IVDOMwFeQAi9hW5xu0SxHxe', 2, '');
INSERT INTO `user` VALUES (33, 'mumu2', '$2a$10$ZVYQiEiwaGgBlZrpQCU2xeM4z7GkYr0x3WJjMe4Y5IYblAsmG85tC', 2, '');
INSERT INTO `user` VALUES (34, 'mumu3', '$2a$10$403dkjqukCAAM63/opzM2u.8dbY3JsKpP9qCgeMzdR0U479IFkEhu', 2, '');
INSERT INTO `user` VALUES (35, 'mumu5', '$2a$10$0FG4IsOdpyh8TCLmw0jvJON.ce29vL2mcIy7N9vCGVhh3WsblCZXu', 2, 'api/file/downloadImage/91ec5183cd8c42afa4f975a280e83ab8.jpg');
INSERT INTO `user` VALUES (36, 'mumu6', '$2a$10$oR1rJKKGJ21cqVmpgxwXM.OADot42K9olT1rjrUpT.n9xxpow8GlG', 3, '');
INSERT INTO `user` VALUES (37, 'mumu7', '$2a$10$S5vVbXuuDNaKhUVLRXRJQe0uZQ09OuOERwAj3/CPAEtBF98Y/5JY2', 3, '');
INSERT INTO `user` VALUES (38, 'mumu8', '$2a$10$64My/pZY46iold5wZBPbkuY2EobsDSucaouY8PTyXsssWKgtjYal6', 3, '');
INSERT INTO `user` VALUES (39, 'mumu9', '$2a$10$LFT8SRuT59QkEzurTPixOuXMp66jVpgoqRKo2.7mYUA1PoFq0VheS', 3, '');
INSERT INTO `user` VALUES (40, 'mumu10', '$2a$10$yC.49/VuXEycmL.EPCP.vun3O/cdKDIa.LujJMsAnwRFkbrUle4iy', 3, '');
INSERT INTO `user` VALUES (42, 'mumuzsl', '$2a$10$1Eifl/DtWNMdwI7dsdrAW.EOdDA9DPdStJxBNcWjk.laDyvLx9h66', 3, 'api/file/downloadImage/c61f0dd0fa224aaf956cee67d02b7920.jpg');

-- ----------------------------
-- Table structure for user_notice
-- ----------------------------
DROP TABLE IF EXISTS `user_notice`;
CREATE TABLE `user_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `notice_uid` bigint(20) NULL DEFAULT NULL,
  `read_status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_notice
-- ----------------------------
INSERT INTO `user_notice` VALUES (134, 3, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (135, 4, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (136, 5, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (137, 6, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (138, 7, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (139, 8, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (140, 9, 159611101749658101, 0);
INSERT INTO `user_notice` VALUES (141, 3, 159621117946614101, 0);
INSERT INTO `user_notice` VALUES (142, 31, 159621117946614101, 0);
INSERT INTO `user_notice` VALUES (143, 4, 159621117946614101, 0);
INSERT INTO `user_notice` VALUES (144, 5, 159621117946614101, 0);
INSERT INTO `user_notice` VALUES (145, 23, 159621117946614101, 0);
INSERT INTO `user_notice` VALUES (146, 3, 159621167401963100, 0);
INSERT INTO `user_notice` VALUES (147, 31, 159621167401963100, 0);
INSERT INTO `user_notice` VALUES (148, 4, 159621167401963100, 0);
INSERT INTO `user_notice` VALUES (149, 5, 159621167401963100, 0);
INSERT INTO `user_notice` VALUES (150, 23, 159621167401963100, 0);
INSERT INTO `user_notice` VALUES (151, 3, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (152, 31, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (153, 32, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (154, 33, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (155, 34, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (156, 35, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (157, 4, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (158, 5, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (159, 23, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (160, 36, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (161, 37, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (162, 38, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (163, 39, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (164, 40, 159625470352910100, 0);
INSERT INTO `user_notice` VALUES (165, 42, 159625470352910100, 0);

SET FOREIGN_KEY_CHECKS = 1;
