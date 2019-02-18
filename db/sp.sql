/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-18 11:08:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
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

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL,
  `certificate_name` varchar(255) DEFAULT NULL,
  `certificate_sponsor` varchar(255) DEFAULT NULL COMMENT '证书发布者',
  `certificate_abstract` varchar(255) DEFAULT NULL,
  `certificate_detail` varchar(255) DEFAULT NULL,
  `certificate_score` varchar(255) DEFAULT NULL,
  `certificate_manager` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `school` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `course_stunum` int(11) DEFAULT NULL COMMENT '最大可容纳学生人数',
  `course_abstract` varchar(255) DEFAULT NULL,
  `course_detail` varchar(255) DEFAULT NULL,
  `course_type` varchar(255) DEFAULT NULL COMMENT '课程类型（1必修，2必修）',
  `course_teacher` varchar(255) DEFAULT NULL COMMENT '任教老师',
  `course_status` varchar(255) DEFAULT NULL COMMENT '课程状态（1：选课时间，2：教学期间，3：发布成绩）',
  `school` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `term` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for matchs
-- ----------------------------
DROP TABLE IF EXISTS `matchs`;
CREATE TABLE `matchs` (
  `id` int(11) NOT NULL,
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
-- Table structure for moral
-- ----------------------------
DROP TABLE IF EXISTS `moral`;
CREATE TABLE `moral` (
  `id` int(11) NOT NULL,
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
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `school_abstract` varchar(255) DEFAULT NULL,
  `school_detail` varchar(255) DEFAULT NULL,
  `school_number` varchar(255) DEFAULT NULL,
  `school_address` varchar(255) DEFAULT NULL,
  `school_build_time` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `id` int(11) NOT NULL,
  `term_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `idnum` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `registered_residence` varchar(255) DEFAULT NULL COMMENT '户籍',
  `email` varchar(255) DEFAULT NULL,
  `useridentify` varchar(255) DEFAULT NULL COMMENT '用户编号（学号，教师编号，管理员编号）',
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '用户类型',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `entrance_time` varchar(255) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL COMMENT '删除标志位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity` (
  `id` int(11) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `user_activity_status` varchar(255) DEFAULT NULL COMMENT '参与活动状态（0:审核中，1：通过，2：不通过）',
  `user_activity_score` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_certificate
-- ----------------------------
DROP TABLE IF EXISTS `user_certificate`;
CREATE TABLE `user_certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `certificate_id` varchar(100) DEFAULT NULL,
  `get_certificate_time` varchar(100) DEFAULT NULL,
  `user_certificate_status` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `course_id` varchar(100) DEFAULT NULL,
  `user_course_score` double(100,0) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_match
-- ----------------------------
DROP TABLE IF EXISTS `user_match`;
CREATE TABLE `user_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `match_id` varchar(100) DEFAULT NULL,
  `user_match_status` varchar(255) DEFAULT NULL COMMENT '参与比赛状态（0:审核中，1：通过，2：不通过）',
  `user_match_score` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_moral
-- ----------------------------
DROP TABLE IF EXISTS `user_moral`;
CREATE TABLE `user_moral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `moral_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
