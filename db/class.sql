/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-20 11:33:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `class_number` varchar(255) DEFAULT NULL,
  `class_abstract` varchar(255) DEFAULT NULL,
  `class_detail` varchar(255) DEFAULT NULL,
  `headmaster` varchar(255) DEFAULT NULL COMMENT '班主任',
  `school_id` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
