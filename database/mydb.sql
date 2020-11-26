-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(16) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin1','admin1','admin1@unipi.gr'),('admin2','admin2','admin2@unipi.gr'),('admin3','admin3','admin3@unipi.gr');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `bills_id` int NOT NULL,
  `bills_month` varchar(45) DEFAULT NULL,
  `telephone_number_telephone_number_id` int NOT NULL,
  `programme_id_programme` int NOT NULL,
  `billscost` int DEFAULT NULL,
  PRIMARY KEY (`bills_id`),
  KEY `fk_bills_telephone_number1_idx` (`telephone_number_telephone_number_id`),
  KEY `fk_bills_telephone_number2_idx` (`programme_id_programme`),
  CONSTRAINT `fk_bills_telephone_number1` FOREIGN KEY (`telephone_number_telephone_number_id`) REFERENCES `telephone_number` (`telephone_number_id`),
  CONSTRAINT `fk_bills_telephone_number2` FOREIGN KEY (`programme_id_programme`) REFERENCES `telephone_number` (`telephone_number_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'may',1,1,10),(2,'may',2,2,8),(3,'may',3,1,10),(4,'may',4,3,7),(5,'june',1,1,10),(6,'june',2,2,8),(7,'june',3,1,10),(8,'june',4,3,7),(9,'june',5,2,8),(10,'july',1,1,10);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calls`
--

DROP TABLE IF EXISTS `calls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calls` (
  `calls_id` int NOT NULL,
  `calls_duration` int DEFAULT NULL,
  `calls_timestamp` datetime DEFAULT NULL,
  `bill_bills_id1` int NOT NULL,
  PRIMARY KEY (`calls_id`),
  KEY `fk_calls_bill2_idx` (`bill_bills_id1`),
  CONSTRAINT `fk_calls_bill2` FOREIGN KEY (`bill_bills_id1`) REFERENCES `bill` (`bills_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calls`
--

LOCK TABLES `calls` WRITE;
/*!40000 ALTER TABLE `calls` DISABLE KEYS */;
INSERT INTO `calls` VALUES (1,10,'2020-05-20 09:34:54',1),(2,5,'2020-06-22 10:30:21',1),(3,25,'2020-05-21 13:15:01',2),(4,15,'2020-06-24 16:04:36',2),(5,17,'2020-06-24 16:04:37',1),(6,4,'2020-06-24 16:04:38',3);
/*!40000 ALTER TABLE `calls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `username` varchar(16) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `surname` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `tax_number` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `seller_username` varchar(16) CHARACTER SET utf8 NOT NULL,
  `programme_id_programme` int NOT NULL,
  `telephone_number` int DEFAULT NULL,
  PRIMARY KEY (`username`,`seller_username`),
  KEY `fk_CUSTOMER_SELLER_idx` (`seller_username`),
  KEY `fk_customer_programme1_idx` (`programme_id_programme`),
  CONSTRAINT `fk_customer_programme1` FOREIGN KEY (`programme_id_programme`) REFERENCES `programme` (`id_programme`),
  CONSTRAINT `fk_CUSTOMER_SELLER` FOREIGN KEY (`seller_username`) REFERENCES `seller` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('cus1','cus1','cus1@unipi.gr','dimi','pap','123456','seller1',1,693882403),('cus2','cus2','cus2@unipi.gr','cus2','cus2','57931','seller1',2,2103579864),('dim','dim','dim@unipi.gr','dimitra','papoul','36884','seller2',2,2108976453),('dim1','dim1','dim1@unipi.gr','dimitra','spil','25363','seller3',1,2105463646),('geo','geo','geo@unipi.gr','george','spil','98765','seller2',3,2109884626);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programme`
--

DROP TABLE IF EXISTS `programme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programme` (
  `id_programme` int NOT NULL,
  `programme_description` varchar(45) DEFAULT NULL,
  `programme_speechtime` int DEFAULT NULL,
  `programme_sms_number` int DEFAULT NULL,
  `programme_data` int DEFAULT NULL,
  `programme_cost` int DEFAULT NULL,
  PRIMARY KEY (`id_programme`),
  UNIQUE KEY `id_programme_UNIQUE` (`id_programme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programme`
--

LOCK TABLES `programme` WRITE;
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;
INSERT INTO `programme` VALUES (1,'all in one',1500,1500,5,10),(2,'sms\'o\'clock',300,10000,2,8),(3,'callmemaybe',10000,100,2,7),(4,'summeraki',500,500,20,15);
/*!40000 ALTER TABLE `programme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller` (
  `username` varchar(16) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `admin_username` varchar(16) NOT NULL,
  PRIMARY KEY (`username`,`admin_username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_SELLER_ADMIN1_idx` (`admin_username`),
  CONSTRAINT `fk_SELLER_ADMIN1` FOREIGN KEY (`admin_username`) REFERENCES `admin` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
INSERT INTO `seller` VALUES ('seller1','seller1','seller1@unipi.gr','admin1'),('seller2','seller2','seller2@unipi.gr','admin2'),('seller3','seller3','seller3@unipi.gr','admin1'),('seller4','seller4','seller4@unipi.gr','admin2');
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telephone_number`
--

DROP TABLE IF EXISTS `telephone_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telephone_number` (
  `telephone_number_id` int NOT NULL,
  `telephone_number_area` varchar(45) DEFAULT NULL,
  `telephone_number_type` varchar(45) DEFAULT NULL,
  `telephone_number_category` varchar(45) DEFAULT NULL,
  `programme_id_programme` int NOT NULL,
  `telephone_number` int DEFAULT NULL,
  PRIMARY KEY (`telephone_number_id`),
  KEY `fk_telephone_number_programme1_idx` (`programme_id_programme`),
  CONSTRAINT `fk_telephone_number_programme1` FOREIGN KEY (`programme_id_programme`) REFERENCES `programme` (`id_programme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telephone_number`
--

LOCK TABLES `telephone_number` WRITE;
/*!40000 ALTER TABLE `telephone_number` DISABLE KEYS */;
INSERT INTO `telephone_number` VALUES (1,'agdim','kinhto','foititiko',1,693882403),(2,'peiraias','kinhto','foitiko',2,2108976453),(3,'kallithea','kinhto','foititiko',1,2105463646),(4,'agparaskeyh','kinhto','foititiko',3,2109884626),(5,'agdim','kinhto','oikogeneiako',2,2103579864);
/*!40000 ALTER TABLE `telephone_number` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-09 13:52:40
