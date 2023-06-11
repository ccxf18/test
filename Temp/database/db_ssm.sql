/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库Root
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : db_ssm

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/06/2021 08:59:31
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`    int(11) NOT NULL,
    `name`  varchar(255) DEFAULT NULL,
    `money` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Records of paper
-- ----------------------------
