-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tax_report_system
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaints` (
  `id_complaint` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `completion_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_complaint`),
  KEY `fk_complaints_persons1_idx` (`id_person`),
  CONSTRAINT `fk_complaints_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1,1,'dsaf','2019-05-18 15:07:02'),(2,1,'sadfsadf','2019-05-18 15:21:35'),(3,1,'dsafasfsafdasfsa123asdfsafsafsafdasfdasfsdafs','2019-05-19 05:37:24'),(4,13,'he is bad guy','2019-06-01 08:06:00');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `id_person` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `id_role` int(10) unsigned NOT NULL,
  `id_inspector` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_person`),
  KEY `fk_persons_roles1_idx` (`id_role`),
  KEY `fk_persons_persons1_idx` (`id_inspector`),
  CONSTRAINT `fk_persons_persons1` FOREIGN KEY (`id_inspector`) REFERENCES `persons` (`id_person`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_persons_roles1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (-1,'test','test','test','1',1,1),(1,'ADMIN','ADMIN','admin@gmail.com','1',1,1),(2,'John','Snow','aegon_tg@gmail.com','1',2,1),(11,'Max','Nesterchuk','max@gmail.com','1',1,1),(12,'Katryn','Petrova','kd@gmail.com','1',1,1),(13,'Administrator','Admin','a@g.c','1',1,1),(14,'Client','Cl','c@g.c','1',2,1),(15,'Client1','cl1','c1@g.c','1',2,1),(16,'Client2','cl2','c2@g.c','1',2,1),(17,'Client3','CL3','c3@g.c','1',2,13);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons_has_taxable_items`
--

DROP TABLE IF EXISTS `persons_has_taxable_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons_has_taxable_items` (
  `id_person` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`id_person`,`id_item`),
  KEY `fk_persons_has_taxable_items_taxable_items1_idx` (`id_item`),
  KEY `fk_persons_has_taxable_items_persons1_idx` (`id_person`),
  CONSTRAINT `fk_persons_has_taxable_items_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_persons_has_taxable_items_taxable_items1` FOREIGN KEY (`id_item`) REFERENCES `taxable_items` (`id_item`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons_has_taxable_items`
--

LOCK TABLES `persons_has_taxable_items` WRITE;
/*!40000 ALTER TABLE `persons_has_taxable_items` DISABLE KEYS */;
INSERT INTO `persons_has_taxable_items` VALUES (-1,1,1),(1,1,1),(1,2,122),(2,1,123),(2,2,20),(2,3,5),(14,1,1),(14,2,1),(14,3,1),(15,1,1),(15,2,1),(17,1,200),(17,2,300),(17,3,123);
/*!40000 ALTER TABLE `persons_has_taxable_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `taxpayer_code` varchar(45) DEFAULT NULL,
  `completion_time` timestamp NULL DEFAULT NULL,
  `total_amount_of_property` int(11) DEFAULT NULL,
  `is_accepted` tinyint(1) unsigned zerofill DEFAULT NULL,
  `should_be_changed` tinyint(1) unsigned zerofill DEFAULT NULL,
  `inspector_comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_report`),
  KEY `fk_reports_persons1_idx` (`id_person`),
  CONSTRAINT `fk_reports_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,2,'AASSFSAF','1123123123','2019-05-18 13:09:26',1,1,0,'asdf'),(4,2,'DOX123123','123213','2019-05-19 05:38:24',0,1,0,'лд'),(7,2,'asfdsaf','12313','2019-05-19 06:01:28',1365,1,1,'sdfsdfa'),(9,2,'asfdsaf','123','2019-05-19 06:02:58',1365,0,1,'123123'),(11,2,'DOX123123','123','2019-05-19 06:03:27',1365,0,0,'asdf        asdfasfd                        '),(12,17,'COMP3','33333','2019-05-30 18:49:50',12690,1,1,'comm3'),(13,17,'epam','1234567890','2019-06-01 08:05:07',12690,1,1,'das ist Gut'),(14,14,'ep','12323324','2019-06-02 19:09:08',57,0,0,NULL);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_role` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_role` varchar(15) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `id` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'INSPECTOR'),(2,'CLIENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxable_items`
--

DROP TABLE IF EXISTS `taxable_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxable_items` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `name_item` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxable_items`
--

LOCK TABLES `taxable_items` WRITE;
/*!40000 ALTER TABLE `taxable_items` DISABLE KEYS */;
INSERT INTO `taxable_items` VALUES (1,'square meter office space tax',9),(2,'employee tax',15),(3,'per one million income tax',33);
/*!40000 ALTER TABLE `taxable_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'tax_report_system'
--

--
-- Dumping routines for database 'tax_report_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-03 16:28:26
