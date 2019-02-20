/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-20 08:59:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
