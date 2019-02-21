/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-21 09:22:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for matchs
-- ----------------------------
DROP TABLE IF EXISTS `matchs`;
CREATE TABLE `matchs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_name` varchar(255) DEFAULT NULL,
  `match_time` varchar(255) DEFAULT NULL,
  `match_abstract` varchar(255) DEFAULT NULL,
  `match_detail` varchar(255) DEFAULT NULL,
  `match_leader` varchar(255) DEFAULT NULL,
  `match_sponsor` varchar(255) DEFAULT NULL COMMENT '比赛主办方',
  `match_level` varchar(255) DEFAULT NULL COMMENT '比赛等级（1：国际比赛、2:全国、3:省级、4：市级、5县级、6：校级、7：院级、8：班级）',
  `match_status` varchar(255) DEFAULT NULL COMMENT '比赛状态（1：报名、2：比赛中、3：比赛完毕、4：出成绩）',
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of matchs
-- ----------------------------
