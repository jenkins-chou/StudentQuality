-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: sp
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) DEFAULT NULL,
  `activity_time` varchar(255) DEFAULT NULL,
  `activity_leader` varchar(255) DEFAULT NULL,
  `activity_abstract` varchar(255) DEFAULT NULL,
  `activity_detail` varchar(255) DEFAULT NULL,
  `activity_stunum` varchar(255) DEFAULT NULL,
  `activity_status` varchar(255) DEFAULT NULL COMMENT '活动状态（1：报名时间、2：活动时间、3：活动结束）',
  `activity_score` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'活动名称','时间','null','年前','介绍','3','null','2','null','无备注','delete'),(2,'青少年爱心植树活动','2019年3月20日',NULL,'活动简介','详细介绍','30',NULL,'2',NULL,'无备注','normal'),(3,'全国大学生创新创业活动宣讲会','2019年4月20日',NULL,'无介绍','无介绍','200',NULL,'3',NULL,'无备注','normal');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_name` varchar(255) DEFAULT NULL,
  `certificate_sponsor` varchar(255) DEFAULT NULL COMMENT '证书发布者',
  `certificate_abstract` varchar(255) DEFAULT NULL,
  `certificate_detail` varchar(255) DEFAULT NULL,
  `certificate_score` varchar(255) DEFAULT NULL,
  `certificate_manager` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'四级合格证书','华南农业大学','简介','管理员','3','管理员','normal'),(2,'证书','','','','23.9','','delete'),(3,'六级合格证书','','','','3','','normal'),(4,'计算机二级证书','','','','3','','normal'),(5,'初级软件工程师','','','','5','','normal'),(6,'中级软件工程师','','','','6','','normal'),(7,'志愿者证书','','','','0.5','','normal'),(8,'国家奖学金证书','','','','10','','normal'),(9,'国家励志奖学金证书','','','','2','','normal'),(10,'校级奖学金一等奖证书','','','','3','','normal'),(11,'校级奖学金二等奖证书','','','','2','','normal'),(12,'校级奖学金三等奖证书','','','','1','','normal');
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'管理1182班',NULL,'班级简介','班级介绍','班主任','4','中国农业大学','2','管理学院','1550633805','normal'),(2,'管理1172班',NULL,'简介','介绍','班主任','4','中国农业大学','2','管理学院','1550736507','normal');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'管理学院','4','中国农业大学',NULL,NULL,'delete'),(2,'管理学院','4','中国农业大学','1550629091',NULL,'normal'),(3,'经济学院','4','中国农业大学','1550629124',NULL,'normal'),(4,'计算机学院','4','中国农业大学','1550629137',NULL,'normal'),(5,'数学学院','4','中国农业大学','1550629354',NULL,'normal');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `course_time` varchar(255) DEFAULT NULL,
  `course_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'管理学','','','','必修','正常开课','3','4','2','1','5','中国农业大学','管理学院','2019-2020学年第一学期','吴磊音','1550799565','','normal','每周一  9.00-11.00am','笃行楼423'),(2,'农业经济','','','','必修','正常开课','5','4','3','1','5','中国农业大学','经济学院','2019-2020学年第一学期','吴磊音','1550803091','','normal',NULL,NULL),(3,'java程序设计','','','','选修','正常开课','3','4','4','1','8','中国农业大学','计算机学院','2019-2020学年第一学期','苏珊','1550803199','','normal','周一','主校区');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchs`
--

DROP TABLE IF EXISTS `matchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchs`
--

LOCK TABLES `matchs` WRITE;
/*!40000 ALTER TABLE `matchs` DISABLE KEYS */;
INSERT INTO `matchs` VALUES (1,'比赛测试','','','',NULL,'','校级','',NULL,'','delete'),(2,'全国华夏杯数学建模初赛','时间','无介绍','无介绍','null','主办方','全国','正常','1550715258','无备注','normal'),(3,'','','','',NULL,'','校级','比赛中','1550715565','','delete'),(4,'测试比赛。','时间','简介','介绍','null','主办方','全国','报名中','1550716127','','delete'),(5,'全国泰迪杯数学建模比赛','2019年','简介','数学建模简介',NULL,'全国数学建模组委','全国','报名中','1550917782','无备注','normal');
/*!40000 ALTER TABLE `matchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moral`
--

DROP TABLE IF EXISTS `moral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moral`
--

LOCK TABLES `moral` WRITE;
/*!40000 ALTER TABLE `moral` DISABLE KEYS */;
INSERT INTO `moral` VALUES (1,'上课全勤','说明','详细说明','加分','null','null',3,'null','无备注','normal'),(2,'上课迟到10分钟以内','','','减分',NULL,NULL,3,NULL,'','normal'),(3,'考核名称','','','减分',NULL,NULL,3,NULL,'','delete');
/*!40000 ALTER TABLE `moral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'名称','校训','介绍',NULL,'地址','2019年02月19日 20时15分51秒','1550578759','','delete'),(2,'华南农业大学','华农校训','华农介绍',NULL,'华农地址','2019年02月19日 20时15分51秒','1550578780','','delete'),(3,'测试','校训','还',NULL,'学校','2019年02月19日 20时21分32秒','1550578897','','delete'),(4,'中国农业大学','校训','介绍',NULL,'地址','2019年02月19日 20时30分35秒','1550579441','','normal'),(5,'华北农业大学','校训','介绍',NULL,'地址','2019年02月20日 10时42分19秒','1550630554','','normal'),(6,'华南农业大学','校训','介绍',NULL,'地址','2019年02月20日 12时42分58秒','1550637782','','normal');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'2019-2020学年第一学期','1550740898','无备注。。','normal'),(2,'测试','1550741154','无备注','delete'),(3,'2019-2020学年第二学期','1550763033','无备注','normal');
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `class_id` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL COMMENT '删除标志位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','admin','管理员','upload/InR05383XfgYLFmIUkXsk-NK.png','null','null','null','null','null','null','null','null','null','null','null','3','null','null','null','null','null','null','null','null',NULL,'normal'),(3,'123','123','吴磊','upload/UrYt9EfgQfnuJE5LKZDSTHIk.jpg','没有签名','男','14','4409211998','汉族','广东','null','123','123','暂无','暂无','1','null','null','1','2','4','管理1182班','管理学院','中国农业大学',NULL,'normal'),(4,'t1','t1','吴磊','','','','','','','','','','','','','2',NULL,'','','2','4','','管理学院','中国农业大学',NULL,'delete'),(5,'t2','t2','吴磊音','upload/EkxMjaGo4q2Y1CnS-nnfGr8u.jpg','标签','男','35','123456786','汉','广东','邮箱','123456','123456789','地址','1996年','2','null','2017年','','4','4','','计算机学院','中国农业大学',NULL,'normal'),(6,'456','456','张晓欣','null','null','女','null','null','null','null','null','null','null','null','null','1','null','null','1','2','4','管理1182班','管理学院','中国农业大学',NULL,'normal'),(7,'789','789','周宇','null','null','null','null','null','null','null','null','null','null','null','null','1','null','null','1','2','4','管理1182班','管理学院','中国农业大学',NULL,'normal'),(8,'t3','t3','苏珊','','','女','','','','','','','','','','2',NULL,'','','3','4','','经济学院','中国农业大学',NULL,'normal'),(9,'147','147','张三','null','null','null','null','null','null','null','null','null','null','null','null','1','null','null','2','2','4','管理1172班','管理学院','中国农业大学',NULL,'normal'),(10,'258','258','null','null','null','null','null','null','null','null','null','null','null','null','null','1','null','null','2','2','4','管理1172班','管理学院','中国农业大学',NULL,'normal');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `user_activity_status` varchar(255) DEFAULT NULL COMMENT '参与活动状态（0:审核中，1：通过，2：不通过）',
  `user_activity_score` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
INSERT INTO `user_activity` VALUES (1,'3','2','良好','5','1550935239','备注','delete'),(2,'3','3','优秀','5','1550935377','无备注','normal'),(3,'3','2','良好','3','1550938801','无备注','normal');
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_certificate`
--

DROP TABLE IF EXISTS `user_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_certificate`
--

LOCK TABLES `user_certificate` WRITE;
/*!40000 ALTER TABLE `user_certificate` DISABLE KEYS */;
INSERT INTO `user_certificate` VALUES (2,'3','4','2019年',NULL,'1550919563','无备注','normal'),(3,'3','3','无聊',NULL,'1550920258','无备注','delete'),(4,'3','7','时间','null','1550920352','备注','delete'),(5,'3','6','时间',NULL,'1550921142','备注','delete');
/*!40000 ALTER TABLE `user_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `course_id` varchar(100) DEFAULT NULL,
  `class_id` varchar(100) DEFAULT NULL,
  `user_course_score` double(100,0) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course`
--

LOCK TABLES `user_course` WRITE;
/*!40000 ALTER TABLE `user_course` DISABLE KEYS */;
INSERT INTO `user_course` VALUES (1,'3','1','1',NULL,'1550821779',NULL,'delete'),(2,'6','1','1',NULL,'1550821779',NULL,'delete'),(3,'7','1','1',NULL,'1550821779',NULL,'delete'),(4,'3','1','1',NULL,'1550824462',NULL,'delete'),(5,'6','1','1',NULL,'1550824462',NULL,'delete'),(6,'7','1','1',NULL,'1550824462',NULL,'delete'),(7,'3','1','1',NULL,'1550824507',NULL,'delete'),(8,'6','1','1',NULL,'1550824507',NULL,'delete'),(9,'7','1','1',NULL,'1550824507',NULL,'delete'),(10,'3','1','1',76,'1550824601',NULL,'noraml'),(11,'6','1','1',NULL,'1550824601',NULL,'delete'),(12,'7','1','1',35,'1550824601',NULL,'noraml'),(13,'3','2','1',NULL,'1550902741',NULL,'delete'),(14,'6','2','1',NULL,'1550902741',NULL,'delete'),(15,'7','2','1',NULL,'1550902741',NULL,'delete'),(16,'6','1','1',45,'1550906003',NULL,'normal'),(19,'6','2','1',NULL,'1550906276',NULL,'delete'),(20,'3','3','1',90,'1550909085',NULL,'normal');
/*!40000 ALTER TABLE `user_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_match`
--

DROP TABLE IF EXISTS `user_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_match`
--

LOCK TABLES `user_match` WRITE;
/*!40000 ALTER TABLE `user_match` DISABLE KEYS */;
INSERT INTO `user_match` VALUES (1,'3','5','冠军','3','1550916004','备注','delete'),(2,'3','5','一等奖','5','1550917916','无备注','normal'),(3,'6','5','冠军','3','1550942002','','normal');
/*!40000 ALTER TABLE `user_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_moral`
--

DROP TABLE IF EXISTS `user_moral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_moral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `moral_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_moral`
--

LOCK TABLES `user_moral` WRITE;
/*!40000 ALTER TABLE `user_moral` DISABLE KEYS */;
INSERT INTO `user_moral` VALUES (1,'3','1','1550938542','备注备注','delete'),(2,'3','2','1550938589','2019年2月21 英语课迟到','normal'),(3,'6','2','1550942094','','normal'),(4,'3','2','1550982126','无备注','normal'),(5,'3','1','1551075377','无备注','normal');
/*!40000 ALTER TABLE `user_moral` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-02  2:38:03
