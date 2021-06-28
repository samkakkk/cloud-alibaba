/*
 Navicat Premium Data Transfer

 Source Server         : alibaba
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 172.31.129.54:3306
 Source Schema         : cloud_alibaba

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 17/06/2021 19:42:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ACCOUNT_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `AMOUNT` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UK_ACCOUNT_CODE`(`ACCOUNT_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'javadaily', 'JAVA日知录', 800.00);
INSERT INTO `account` VALUES (17, 'jianzh5', 'jianzh5', 300.00);

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES (1, 'Re_Cwert2020', 'Each');
INSERT INTO `blog_user` VALUES (2, 's13018077177', 'youta');
INSERT INTO `blog_user` VALUES (3, 'linzi2524065297', '林子');
INSERT INTO `blog_user` VALUES (4, 'tan19950827', '自己吓自己');
INSERT INTO `blog_user` VALUES (5, 'xzh609599589', 'Robin');
INSERT INTO `blog_user` VALUES (6, 'aliluya007', '李鹏');
INSERT INTO `blog_user` VALUES (7, 'iloveyou-225', 'iloveyou-225');
INSERT INTO `blog_user` VALUES (8, 'a1165519392', 'Alan');
INSERT INTO `blog_user` VALUES (9, 'cherry4133', 'Cruiii');
INSERT INTO `blog_user` VALUES (10, 'dli_1234567890', 'dli');
INSERT INTO `blog_user` VALUES (11, 'wuzhong339483', '武中华');
INSERT INTO `blog_user` VALUES (12, 'www_1666519200_com', '曹燕尾');
INSERT INTO `blog_user` VALUES (13, 'xiao833969', '成');
INSERT INTO `blog_user` VALUES (14, 'zuoguobin520', 'BGM');
INSERT INTO `blog_user` VALUES (15, 'M1nG377', 'M1nG');
INSERT INTO `blog_user` VALUES (16, 'yrf1004388972', '锋');
INSERT INTO `blog_user` VALUES (17, 'yanghang594740', '喧嚣掩埋寂寥');
INSERT INTO `blog_user` VALUES (18, 'touchfordevil', '霍尔普斯');
INSERT INTO `blog_user` VALUES (19, 'h18559622286', '黄义');
INSERT INTO `blog_user` VALUES (20, 'supersailer', 'sailor');
INSERT INTO `blog_user` VALUES (21, 'wxid_i8t7vxcy6lzq21', 'C.K.D');
INSERT INTO `blog_user` VALUES (22, 'dearmisslong', 'dearmisslong');
INSERT INTO `blog_user` VALUES (23, 'qzg961', 'zzzz');
INSERT INTO `blog_user` VALUES (24, 'jinsheng781343', 'lolkak');
INSERT INTO `blog_user` VALUES (25, 'zhan1019576155', '正方体男孩');
INSERT INTO `blog_user` VALUES (26, 'rayzhng', 'ray');
INSERT INTO `blog_user` VALUES (27, 'Iloveyoulian', '亮');
INSERT INTO `blog_user` VALUES (28, 'nzplj1001', '活着就得折腾');
INSERT INTO `blog_user` VALUES (29, 'Lq18328079445', '与我常在');
INSERT INTO `blog_user` VALUES (30, 'lf1213xx', '梦离丶');
INSERT INTO `blog_user` VALUES (31, 'yanfei_baby', 'Cake');
INSERT INTO `blog_user` VALUES (32, 'qzp19910402', '大鹏');
INSERT INTO `blog_user` VALUES (33, 'wodearong', '好天气');
INSERT INTO `blog_user` VALUES (34, 'xiaohe68', 'xiaohe68');
INSERT INTO `blog_user` VALUES (35, 'wyyyx0512-', 'lizi');
INSERT INTO `blog_user` VALUES (36, 'LiaoWenQingWX', '以一敌百');
INSERT INTO `blog_user` VALUES (37, 'wx_chongsha', 'chongsha');
INSERT INTO `blog_user` VALUES (38, 'd18221974588', '邓鹏');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `PRODUCT_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `COUNT` int(11) NULL DEFAULT 0 COMMENT '库存数量',
  `PRICE` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UK_PRODUCT_CODE`(`PRODUCT_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'demoDataxx', 'demoDataxx', 10, 10.00);
INSERT INTO `product` VALUES (2, 'P002', '手表', 92, 250.00);
INSERT INTO `product` VALUES (3, 'P003', '键盘', 100, 5.00);
INSERT INTO `product` VALUES (4, 'P004', '辣条', 987, 20.00);

-- ----------------------------
-- Table structure for rocketmq_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `rocketmq_transaction_log`;
CREATE TABLE `rocketmq_transaction_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事务ID',
  `log` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rocketmq_transaction_log
-- ----------------------------
INSERT INTO `rocketmq_transaction_log` VALUES (16, 'beeead03-6ccf-4901-b52d-ed47e95da8fa', '执行删除订单操作');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_NO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ACCOUNT_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PRODUCT_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COUNT` int(11) NULL DEFAULT 0,
  `AMOUNT` decimal(10, 2) NULL DEFAULT 0.00,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'VALID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (43, '3881a524-93c0-41af-9f83-b5bbf6da9dbe', 'jianzh5', 'P004', 10, 200.00, 'INVALID');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
