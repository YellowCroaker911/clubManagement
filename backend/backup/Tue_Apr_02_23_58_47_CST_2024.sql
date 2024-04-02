-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: club
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `club`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `club` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `club`;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `club_id` bigint(20) NOT NULL COMMENT '社团id',
  `name` varchar(255) NOT NULL COMMENT '活动名称',
  `info` varchar(1023) NOT NULL COMMENT '活动信息',
  `title` varchar(255) NOT NULL COMMENT '活动主题',
  `begin_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活结束时间',
  `sign_begin_time` datetime NOT NULL COMMENT '最早签到时间',
  `sign_end_time` datetime NOT NULL COMMENT '最迟签到时间',
  `address` varchar(255) NOT NULL COMMENT '活动地点',
  `sign` varchar(255) NOT NULL COMMENT '报名方式',
  `money` bigint(20) NOT NULL COMMENT '活动缴费（单位是分）',
  `join_people` int(11) NOT NULL DEFAULT '0' COMMENT '参加人数',
  `attendance_people` int(11) NOT NULL DEFAULT '0' COMMENT '签到人数',
  `summary` varchar(1023) DEFAULT NULL COMMENT '活动总结',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `club_id` (`club_id`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'健康中国春播行动全国项目','该项目以基层为重点，在全国范围内开展形式多样的中医诊疗技术专项培训、健康科普宣传、专家巡诊、免费中医智能体质辨识、慢病筛查等活动，提升基层医务人员健康管理技能，加强基层的健康管理水平和中医药诊疗的标准化水平，提高基层群众对医疗卫生服务的满意度和获得感。','志愿者','2024-03-14 00:00:00','2025-03-01 00:00:00','2025-03-01 00:00:00','2025-03-01 00:00:00','国英园-1号楼','1376000606@qq.com',100,2,0,NULL,'2024-03-31 01:23:49','2024-03-31 01:44:12',0),(2,1,'中国公益万里行志愿者','1.服务内容与职责：积极传播公益理念，普及公益常识，运用互联网技术帮助慈善组织、公益项目、公益人做好公益理念传播，助力其发展，为国家共同富裕发展和现代化治理体系完善作出公益行业应有贡献。同时让中国公益慈善事业的发展状况、中国的公益文化、现代公益理念更好向社会和世界展示，唤醒公众的公益意识，强化网络文明建设。 2.活动介绍：严格审核、规范制作新媒体产品，利用新媒体平台和账号作为网络传播载体。同时赋能受益对象在新媒体宣传和新媒体方面的技能服务和培训。 民政部门备案号： 53330000A933770284A20137 3.招募对象： ①新媒体从业类：专业自媒体人、业余自媒体人 ②在校学生类：本专科新传类、摄影类专业（拍摄工具不限)大学生，或非专业具备一定同技能的大学生/研究生。 ③社会人士类：可熟练使用相机、手机的拍摄/视频制作软件的各行业人员。 4.活动流程： ①创作：志愿者参与本地线下公益活动的拍摄、公益活动视频剪辑，基金会负责线上新媒体推广的方式开展活动。 ②审核：由河北省新益公益基金等公益机构老师进行内容审核（包含意识形态)。 ③发布：通过中国公益万里行机构官宣平台进行项目的展示发','志愿者','2024-02-25 00:00:00','2025-02-06 00:00:00','2024-03-31 01:26:25','2025-03-20 00:00:00','河北省邯郸市丛台区人民东路312号','123@163.com',0,2,0,NULL,'2024-03-31 01:26:28','2024-03-31 01:44:18',0),(3,1,'珍爱湿地守护未来','珍爱湿地 守护未来 推进湿地保护全球行动 ---共同珍爱湿地、共创美好湿地、共谋湿地保护 古往今来，人类逐水而居，文明伴水而生，人类生产生活同湿地有着密切联系。共同珍爱湿地、共创美好湿地、共谋湿地保护，具有十分重要的意义。深化认识、加强合作，共同推进湿地保护全球行动。 大力宣传湿地的生态功能和保护修复湿地的重要性，积极倡导“共同珍爱湿地 共创美好湿地 共谋湿地保护”的社会风尚。每年的2月2日是世界湿地日。这是湿地国际联盟组织于1996年3月确定的。从1997年开始，世界各国在这一天都举行不同形式的活动来宣传保护自然资源和生态环境。湿地与森林、海洋并称为地球三大生态系统，具有涵养水源、调节气候、改善环境、维护生物多样性等多种生态功能，被形容为“地球之肾”。 招募新青年志愿者：线上文案活动内容策划、活动招募、推广等志愿服务。新媒体传播，拍摄保护区自然风光、动植物、课程活动和重大事件等影像资料及新媒体运营、视频剪辑、制作等志愿服务。协助参与保护区开展生物多样性调查及保护志愿服务。 道阻且长，行则将至；行而不辍，未来可期。让我们共同努力，谱写全球湿地保护新篇章。','志愿者','2024-02-25 00:00:00','2025-02-07 00:00:00','2024-03-14 00:00:00','2027-03-16 00:00:00','中国黄海湿地博物馆','987654@foxmail.com',10000,2,1,NULL,'2024-03-31 01:28:23','2024-03-31 01:44:32',0),(4,3,'这是一个卖书活动','这是一个卖书活动的信息','义卖','2024-02-25 01:37:35','2024-03-13 00:00:00','2024-02-25 01:37:35','2024-03-13 00:00:00','操场','123456@scau.edu.cn',0,1,0,NULL,'2024-03-31 01:38:13','2024-03-31 01:44:24',0);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_release_activity` AFTER INSERT ON `activity` FOR EACH ROW begin

    update club set activity_number = club.activity_number + 1 where club.id = new.club_id;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_delete_activity` AFTER DELETE ON `activity` FOR EACH ROW begin

    update club set activity_number = club.activity_number - 1 where club.id = OLD.club_id;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `club` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '社团id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `avatar` varchar(1023) DEFAULT NULL COMMENT '头像',
  `info` varchar(1023) DEFAULT NULL COMMENT '社团描述',
  `address` varchar(1023) DEFAULT NULL COMMENT '活动场地',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `member` int(11) NOT NULL DEFAULT '0' COMMENT '成员数量',
  `activity_number` int(11) NOT NULL DEFAULT '0' COMMENT '活动数量',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '公费（单位是分）',
  `president_id` bigint(20) NOT NULL COMMENT '社长id',
  `is_admitted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否审核通过(0-审核中, 1-通过)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社团';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'志愿者的社团','img_2024_03_31012158023.jpg','干志愿者，有志愿时','饭堂','123213@gmail.com',3,3,10200,1,1,'2024-03-31 01:18:31','2024-03-31 01:44:34',0),(3,'义卖有限公司','img_2024_03_31013519623.jpg','卖了的钱是我们的','市场','sellwithoutmoney@scau.edu.cn',1,1,0,1,1,'2024-03-31 01:33:07','2024-03-31 01:38:13',0),(4,'这是一个乱搞社团',NULL,'luangao','家','123@qq.qq',0,0,0,3,0,'2024-03-31 01:41:10','2024-03-31 01:41:10',0);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '名称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(1023) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别（0-男，1-女）',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '用户 0-用户 1-管理员',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '贡献金额（单位是分）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$4BfAUUd54fXcpTn6vsVub.ekqHBFNKTNl1KA.ca4jFdsc2kuBFKMO','管理员','img_2024_03_31011603545.jpg',0,'13111111111','654987@gmail.com',1,10100,'2024-03-31 01:12:10','2024-03-31 01:44:34',0),(2,'user1','$2a$10$6yGEGj4F7Yb27lw/1a751OhhwmPztx0KZvnYNr.3/FD98T11YkRuy','勒布朗','img_2024_03_31013940558.jpg',0,'13642329221','lbl@qq.com',0,100,'2024-03-31 01:38:59','2024-03-31 01:42:33',0),(3,'user2','$2a$10$e2b2sGUl3UcCZxYK.q83jeRMHmQQjE.4XI8xJd7UlnwDZvqr6LzM.','绝缘体','img_2024_03_31014022155.jpg',1,'13111112211','6587@gmail.com',0,0,'2024-03-31 01:40:08','2024-03-31 01:40:08',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_activity` (
  `user_activity_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `activity_id` bigint(20) NOT NULL COMMENT '活动id',
  `pay_status` int(11) NOT NULL DEFAULT '0' COMMENT '0-没缴费 1-缴费',
  `join_status` int(11) NOT NULL DEFAULT '0' COMMENT '0-没签到 1-签到',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_activity_id`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`),
  CONSTRAINT `user_activity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_activity_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社员和活动关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
INSERT INTO `user_activity` VALUES (1,2,1,1,0,'2024-03-31 01:42:28','2024-03-31 01:42:28',0),(2,2,2,0,0,'2024-03-31 01:42:36','2024-03-31 01:42:36',0),(3,2,3,0,0,'2024-03-31 01:42:40','2024-03-31 01:42:40',0),(4,1,1,1,0,'2024-03-31 01:44:12','2024-03-31 01:44:12',0),(5,1,2,1,0,'2024-03-31 01:44:18','2024-03-31 01:44:18',0),(6,1,4,1,0,'2024-03-31 01:44:24','2024-03-31 01:44:24',0),(7,1,3,1,1,'2024-03-31 01:44:31','2024-03-31 01:44:31',0);
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_sign_up_activity` AFTER INSERT ON `user_activity` FOR EACH ROW begin

    update activity set join_people = activity.join_people + 1 where activity.id = new.activity_id;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_user_activity_status_update` AFTER UPDATE ON `user_activity` FOR EACH ROW BEGIN

    DECLARE activity_money bigint;

    

    IF NEW.pay_status = 1 AND OLD.pay_status != 1 THEN

        

        SELECT money INTO activity_money FROM activity WHERE id = NEW.activity_id;

        

        IF activity_money IS NOT NULL THEN

            

            UPDATE club

            SET money = money + activity_money

            WHERE id = (select club_id from activity where id = NEW.activity_id);

            

            UPDATE user

            SET money = money + activity_money

            WHERE id = NEW.user_id;

            

            UPDATE user_club

            SET contribution_money = contribution_money + activity_money

            WHERE user_id = NEW.user_id and club_id = (select club_id from activity where id = NEW.activity_id);

        END IF;

    END IF;

    IF NEW.join_status = 1 AND OLD.join_status != 1 THEN

        update activity set attendance_people = activity.attendance_people + 1 where activity.id = new.activity_id;

    END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_cancel_activity` AFTER DELETE ON `user_activity` FOR EACH ROW begin

    update activity set join_people = activity.join_people - 1 where activity.id = OLD.activity_id;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_club`
--

DROP TABLE IF EXISTS `user_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_club` (
  `user_club_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `club_id` bigint(20) NOT NULL COMMENT '社团id',
  `is_passed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否审核通过(0-审核中, 1-通过)',
  `contribution_money` bigint(20) DEFAULT '0' COMMENT '社团贡献（单位是分）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_club_id`),
  KEY `user_index` (`user_id`),
  KEY `club_index` (`club_id`),
  CONSTRAINT `user_club_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_club_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社员和社团关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_club`
--

LOCK TABLES `user_club` WRITE;
/*!40000 ALTER TABLE `user_club` DISABLE KEYS */;
INSERT INTO `user_club` VALUES (1,1,1,1,10100,'2024-03-31 01:22:07','2024-03-31 01:44:34',0),(2,1,3,1,0,'2024-03-31 01:36:24','2024-03-31 01:36:24',0),(3,2,1,1,100,'2024-03-31 01:39:54','2024-03-31 01:42:33',0),(4,3,3,0,0,'2024-03-31 01:40:50','2024-03-31 01:40:50',0),(5,3,1,1,0,'2024-03-31 01:40:54','2024-03-31 01:40:54',0),(6,1,4,0,0,'2024-03-31 01:55:05','2024-03-31 01:55:05',0);
/*!40000 ALTER TABLE `user_club` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_join_club` AFTER UPDATE ON `user_club` FOR EACH ROW begin

    IF NEW.is_passed = 1 AND OLD.is_passed != 1 THEN

        update club set member = member + 1 where id = new.club_id;

    END IF;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `on_exit_club` AFTER DELETE ON `user_club` FOR EACH ROW begin

    IF OLD.is_passed = 1 THEN

        update club set member = member - 1 where id = OLD.club_id;

    END IF;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `view_club_users`
--

DROP TABLE IF EXISTS `view_club_users`;
/*!50001 DROP VIEW IF EXISTS `view_club_users`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `view_club_users` AS SELECT 
 1 AS `user_id`,
 1 AS `club_id`,
 1 AS `name`,
 1 AS `club_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `club`
--

USE `club`;

--
-- Final view structure for view `view_club_users`
--

/*!50001 DROP VIEW IF EXISTS `view_club_users`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_club_users` AS select `uc`.`user_id` AS `user_id`,`uc`.`club_id` AS `club_id`,`u`.`name` AS `name`,`c`.`name` AS `club_name` from ((`user_club` `uc` left join `user` `u` on((`uc`.`user_id` = `u`.`id`))) left join `club` `c` on((`uc`.`club_id` = `c`.`id`))) where ((`uc`.`is_delete` = 0) and (`u`.`is_delete` = 0) and (`c`.`is_delete` = 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-02 23:58:48
