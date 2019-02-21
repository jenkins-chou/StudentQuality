/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-21 09:22:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) DEFAULT NULL,
  `activity_time` varchar(255) DEFAULT NULL,
  `activity_leader` varchar(255) DEFAULT NULL,
  `activity_abstract` varchar(255) DEFAULT NULL,
  `activity_detail` varchar(255) DEFAULT NULL,
  `activity_stunum` int(11) DEFAULT NULL COMMENT '最大可容纳学生人数',
  `activity_status` varchar(255) DEFAULT NULL COMMENT '活动状态（1：报名时间、2：活动时间、3：活动结束）',
  `activity_score` double(10,0) DEFAULT NULL COMMENT '活动加分',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
