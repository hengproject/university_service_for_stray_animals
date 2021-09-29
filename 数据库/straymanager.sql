-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: straymanager
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `area` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `campus_id` int(11) NOT NULL,
  PRIMARY KEY (`area_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'test',1),(2,'食堂交汇处',1),(3,'test',2),(16,'test03',9);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campus`
--

DROP TABLE IF EXISTS `campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `campus` (
  `campus_id` int(11) NOT NULL AUTO_INCREMENT,
  `campus_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`campus_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campus`
--

LOCK TABLES `campus` WRITE;
/*!40000 ALTER TABLE `campus` DISABLE KEYS */;
INSERT INTO `campus` VALUES (1,'淮安校区'),(2,'南京校区'),(9,'test9');
/*!40000 ALTER TABLE `campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_info`
--

DROP TABLE IF EXISTS `cat_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_info` (
  `cat_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cat_sex` tinyint(1) NOT NULL,
  `cat_age` int(3) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  `last_find_time` timestamp NULL DEFAULT NULL,
  `cat_photo_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cat_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `campus_id` int(11) DEFAULT NULL,
  `cat_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cat_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`cat_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_info`
--

LOCK TABLES `cat_info` WRITE;
/*!40000 ALTER TABLE `cat_info` DISABLE KEYS */;
INSERT INTO `cat_info` VALUES ('0',0,3,'2021-09-25 11:57:40','2021-09-29 06:31:35','2021-09-25 11:57:51','9969996','test_reamark',1,1,'测试','测试猫'),('1',1,4,'2021-09-26 05:37:33','2021-09-29 06:26:09','2021-09-26 05:37:38','9c2641bcac6e4164ab1fe489086a92ba','testremark2',1,1,'测试','测试猫2');
/*!40000 ALTER TABLE `cat_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_statistics`
--

DROP TABLE IF EXISTS `cat_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_statistics` (
  `cat_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `showtimes` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`cat_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_statistics`
--

LOCK TABLES `cat_statistics` WRITE;
/*!40000 ALTER TABLE `cat_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `cat_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feed` (
  `feed_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `campus_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `area_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `staff_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `feed_time` timestamp NOT NULL,
  `feed_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`feed_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_record`
--

DROP TABLE IF EXISTS `file_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `file_record` (
  `file_id` varchar(64) NOT NULL,
  `file_add` varchar(64) NOT NULL,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `file_record_file_id_uindex` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_record`
--

LOCK TABLES `file_record` WRITE;
/*!40000 ALTER TABLE `file_record` DISABLE KEYS */;
INSERT INTO `file_record` VALUES ('0','default.png'),('9969996','付款码.jpg'),('9c2641bcac6e4164ab1fe489086a92ba','0299173d902b466e95d7de250922f3a7.jpg');
/*!40000 ALTER TABLE `file_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_statistics`
--

DROP TABLE IF EXISTS `site_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `site_statistics` (
  `user_num` int(11) NOT NULL DEFAULT '0',
  `cat_num` int(11) NOT NULL DEFAULT '0',
  `Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_statistics`
--

LOCK TABLES `site_statistics` WRITE;
/*!40000 ALTER TABLE `site_statistics` DISABLE KEYS */;
INSERT INTO `site_statistics` VALUES (4,2,1);
/*!40000 ALTER TABLE `site_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_info`
--

DROP TABLE IF EXISTS `staff_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff_info` (
  `staff_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `staff_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `staff_sex` tinyint(1) NOT NULL DEFAULT '1',
  `staff_age` varchar(8) COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` timestamp NOT NULL,
  `staff_identity` int(11) NOT NULL,
  `create_time` timestamp NOT NULL,
  `staff_remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `qq` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_num` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_info`
--

LOCK TABLES `staff_info` WRITE;
/*!40000 ALTER TABLE `staff_info` DISABLE KEYS */;
INSERT INTO `staff_info` VALUES ('ysh','1',0,'20','2021-09-17 13:05:01',0,'2021-09-03 15:27:15',NULL,'1','123123','996996996'),('test_superuser','2',0,'20','2021-09-06 03:06:39',0,'2021-09-06 03:06:38',NULL,'2',NULL,NULL),('test_manager','3',0,'20','2021-09-04 15:19:30',0,'2021-09-04 15:19:27',NULL,'3',NULL,NULL),('测试普通用户','89020d44944f4b06b2b68f844dc2b301',0,'20','2021-09-12 05:06:09',1,'2021-09-09 16:27:10','','2eac31b7ca3c4ecbba472b370693eb13',NULL,NULL);
/*!40000 ALTER TABLE `staff_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_statistics`
--

DROP TABLE IF EXISTS `staff_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff_statistics` (
  `staff_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `total_login` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `latest_login` timestamp NOT NULL,
  `last_month_login` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `this_week_login` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `last_week_login` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `this_month_login` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_statistics`
--

LOCK TABLES `staff_statistics` WRITE;
/*!40000 ALTER TABLE `staff_statistics` DISABLE KEYS */;
INSERT INTO `staff_statistics` VALUES ('1','5','2021-09-13 14:49:54','5','3','4','7');
/*!40000 ALTER TABLE `staff_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_identity`
--

DROP TABLE IF EXISTS `user_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_identity` (
  `user_id` varchar(50) NOT NULL,
  `user_group` int(11) NOT NULL,
  `special_identity` int(11) DEFAULT NULL,
  `staff_id` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_identity`
--

LOCK TABLES `user_identity` WRITE;
/*!40000 ALTER TABLE `user_identity` DISABLE KEYS */;
INSERT INTO `user_identity` VALUES ('1',0,0,'1'),('2',1,0,'2'),('2eac31b7ca3c4ecbba472b370693eb13',1,0,'89020d44944f4b06b2b68f844dc2b301'),('3',1,1,'3');
/*!40000 ALTER TABLE `user_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_login` (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES ('1','ysh0','200108'),('2','test_superuser','123456'),('2eac31b7ca3c4ecbba472b370693eb13','test_normal_user','test_normal_user'),('3','test_manager','123456');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-29 16:41:52
