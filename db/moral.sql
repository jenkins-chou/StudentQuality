/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-21 09:23:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for moral
-- ----------------------------
DROP TABLE IF EXISTS `moral`;
CREATE TABLE `moral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moral_name` varchar(255) DEFAULT NULL,
  `moral_abstract` varchar(255) DEFAULT NULL,
  `moral_detail` varchar(255) DEFAULT NULL,
  `moral_type` varchar(255) DEFAULT NULL COMMENT '加分or减分（1 or 2）',
  `moral_status` varchar(255) DEFAULT NULL,
  `moral_manager` varchar(255) DEFAULT NULL,
  `moral_score` double(10,0) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moral
-- ----------------------------
