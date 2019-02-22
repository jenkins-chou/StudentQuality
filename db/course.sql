/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : sp

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-02-22 08:56:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `course_stunum` varchar(255) DEFAULT NULL COMMENT '最大可容纳学生人数',
  `course_abstract` varchar(255) DEFAULT NULL,
  `course_detail` varchar(255) DEFAULT NULL,
  `course_type` varchar(255) DEFAULT NULL COMMENT '课程类型（1必修，2必修）',
  `course_status` varchar(255) DEFAULT NULL COMMENT '课程状态（1：选课时间，2：教学期间，3：发布成绩）',
  `course_score` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `term_id` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `term_name` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
