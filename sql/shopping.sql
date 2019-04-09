/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 09/04/2019 14:44:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//商品名称',
  `price` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '//商品价格',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//商品描述',
  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '//商品销售数量',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//商品图片链接地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES (3, 'iphone Xs Max', 9999.00, '分辨率：2688*1242，后置摄像头：双1200万像素，前置摄像头：700万像素', 9, 'https://img12.360buyimg.com/n7/jfs/t1/4528/10/3590/153299/5b997bf5E4a513949/45ab3dd6c35d981b.jpg');
INSERT INTO `item_info` VALUES (5, '小米9', 3299.00, '新品三摄 8GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照游戏智能手机', 15, 'https://img10.360buyimg.com/n7/jfs/t1/30858/32/2918/209997/5c6fcc52Ed2f6e122/d05ed0ce052a5a0c.jpg');
INSERT INTO `item_info` VALUES (6, 'Java从入门到精通', 67.00, '循序渐进，实战讲述：基础知识→核心技术→高级应用→项目实战，符合认知规律。', 0, 'http://img3m1.ddimg.cn/29/32/26912981-1_b_4.jpg');
INSERT INTO `item_info` VALUES (7, '疯狂Java讲义', 106.64, '《疯狂Java讲义》历时十年沉淀，现已升级到第4版，经过无数Java学习者的反复验证，被包括北京大学在内的大量985、211高校的优秀教师引荐为参考资料、选作教材。', 0, 'http://img3m9.ddimg.cn/12/17/23532609-1_l_4.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL DEFAULT 0 COMMENT '//商品对应的id',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '//商品库存数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (3, 3, 191);
INSERT INTO `item_stock` VALUES (4, 5, 285);
INSERT INTO `item_stock` VALUES (5, 6, 200);
INSERT INTO `item_stock` VALUES (6, 7, 50);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` double(10, 2) NOT NULL DEFAULT 0.00,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_price` double(10, 2) NOT NULL DEFAULT 0.00,
  `promo_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019040700000000', 1, 5, 3299.00, 3, 9897.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000100', 1, 3, 9999.00, 3, 29997.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000200', 1, 3, 9999.00, 3, 29997.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000300', 1, 5, 3299.00, 2, 6598.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000400', 1, 5, 3299.00, 1, 3299.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000500', 1, 5, 3299.00, 4, 13196.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000600', 1, 5, 3299.00, 5, 16495.00, 0);
INSERT INTO `order_info` VALUES ('2019040800000700', 1, 3, 8999.00, 1, 9999.00, 3);
INSERT INTO `order_info` VALUES ('2019040800000800', 1, 3, 8999.00, 1, 9999.00, 3);
INSERT INTO `order_info` VALUES ('2019040800000900', 1, 3, 8999.00, 1, 9999.00, 3);

-- ----------------------------
-- Table structure for promo_info
-- ----------------------------
DROP TABLE IF EXISTS `promo_info`;
CREATE TABLE `promo_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_time` datetime(0) NOT NULL DEFAULT '0000-01-01 00:00:00',
  `end_time` datetime(0) NOT NULL DEFAULT '0000-01-01 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_item_price` double(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo_info
-- ----------------------------
INSERT INTO `promo_info` VALUES (1, 'iphone Xs max促销', '2019-04-08 21:00:00', '2019-04-08 22:00:00', 3, 8999.00);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 10, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//用户名',
  `age` int(11) NOT NULL DEFAULT 0 COMMENT '//用户年龄',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '//默认值为0,1代表男性，2代表女性',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//用户注册使用的手机号码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telephone_unique_index`(`telephone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'jacky', 20, 1, '13912345678');
INSERT INTO `user_info` VALUES (3, 'real', 20, 1, '15912345678');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//经加密后的用户登录密码',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '//用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (2, '4QrcOUm6Wau+VuBX8g+IPg==', 1);
INSERT INTO `user_password` VALUES (4, '4QrcOUm6Wau+VuBX8g+IPg==', 3);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1, 'admin');
INSERT INTO `user_roles` VALUES (2, 1, 'customer');
INSERT INTO `user_roles` VALUES (3, 3, 'customer');

SET FOREIGN_KEY_CHECKS = 1;
