/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : zuopin20194071102

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 02/12/2021 23:23:28
*/
CREATE DATABASE IF NOT EXISTS zuopin20194071102;
USE zuopin20194071102;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kind
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind`  (
  `kindNo` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kindName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`kindNo`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('01', '电子产品');
INSERT INTO `kind` VALUES ('02', '家居百货');
INSERT INTO `kind` VALUES ('03', '休闲零食');
INSERT INTO `kind` VALUES ('04', '乳饮酒水');
INSERT INTO `kind` VALUES ('05', '方便速食');
INSERT INTO `kind` VALUES ('06', '粮油调味');
INSERT INTO `kind` VALUES ('07', '个护美妆');
INSERT INTO `kind` VALUES ('08', '母婴用品 ');
INSERT INTO `kind` VALUES ('10', '电脑');
INSERT INTO `kind` VALUES ('11', '饮料');

-- ----------------------------
-- Table structure for loginfo
-- ----------------------------
DROP TABLE IF EXISTS `loginfo`;
CREATE TABLE `loginfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logintime` datetime(0) NULL DEFAULT NULL,
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loginfo
-- ----------------------------
INSERT INTO `loginfo` VALUES (1, 'admin', '2021-12-02 18:11:06', '192.168.0.1', '正常登录');
INSERT INTO `loginfo` VALUES (2, 'admin', '2021-12-02 18:11:32', '192.168.0.1', '用户名或密码错误');
INSERT INTO `loginfo` VALUES (3, 'admin', '2021-12-02 20:43:34', '192.168.0.1', '正常登录');
INSERT INTO `loginfo` VALUES (4, 'admin', '2021-12-02 20:52:48', '192.168.0.1', '正常登录');

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `orderID` int(10) NOT NULL AUTO_INCREMENT,
  `product` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(10) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kindNo` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (1, '泡面', 10, '张三', '05');
INSERT INTO `orderlist` VALUES (2, '锅巴', 10, '张三', '03');
INSERT INTO `orderlist` VALUES (3, '火腿肠', 10, '张三', '03');
INSERT INTO `orderlist` VALUES (4, '果冻', 20, '张三', '03');
INSERT INTO `orderlist` VALUES (5, '机械键盘', 1, '张三', '01');
INSERT INTO `orderlist` VALUES (6, '笔记本电脑', 1, '张三', '01');
INSERT INTO `orderlist` VALUES (7, '鼠标', 1, '张三', '01');
INSERT INTO `orderlist` VALUES (8, '手机', 1, '张三', '01');
INSERT INTO `orderlist` VALUES (9, '打印机', 1, '张三', '01');
INSERT INTO `orderlist` VALUES (10, '巧克力', 3, '张三', '03');
INSERT INTO `orderlist` VALUES (11, '雀巢咖啡', 5, '张三', '04');
INSERT INTO `orderlist` VALUES (12, '红牛', 5, '张三', '04');
INSERT INTO `orderlist` VALUES (13, '可乐', 3, '张三', '04');
INSERT INTO `orderlist` VALUES (14, '雪碧', 6, '张三', '04');
INSERT INTO `orderlist` VALUES (15, '旺仔牛奶', 8, '张三', '04');
INSERT INTO `orderlist` VALUES (16, '矿泉水', 6, '张三', '04');
INSERT INTO `orderlist` VALUES (17, '显示器', 2, '张三', '01');
INSERT INTO `orderlist` VALUES (18, '充电宝', 3, '张三', '01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userType` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001', 'cde', 'c4ca4238a0b923820dcc509a6f75849b', '1@163.com', '02');
INSERT INTO `user` VALUES ('admin', '张三', 'c4ca4238a0b923820dcc509a6f75849b', '3@126.com', '01');

SET FOREIGN_KEY_CHECKS = 1;
