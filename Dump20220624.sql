CREATE DATABASE  IF NOT EXISTS `client_schedule` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `client_schedule`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: client_schedule
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `Appointment_ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Start` datetime DEFAULT NULL,
  `End` datetime DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Customer_ID` int NOT NULL,
  `User_ID` int NOT NULL,
  `Contact_ID` int NOT NULL,
  PRIMARY KEY (`Appointment_ID`),
  KEY `fk_customer_id_idx` (`Customer_ID`),
  KEY `fk_user_id_idx` (`User_ID`),
  KEY `fk_contact_id_idx` (`Contact_ID`),
  CONSTRAINT `fk_contact_id` FOREIGN KEY (`Contact_ID`) REFERENCES `contacts` (`Contact_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`Customer_ID`) REFERENCES `customers` (`Customer_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`User_ID`) REFERENCES `users` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (2,'title','description','location','De-Briefing','2020-05-29 12:00:00','2020-05-29 13:00:00','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',2,2,2),(3,'test','test','test','test','2022-06-22 12:00:00','2022-06-22 12:30:00',NULL,NULL,NULL,NULL,2,1,2),(4,'test','test','test','test','2022-06-22 11:30:00','2022-06-22 12:00:00',NULL,NULL,NULL,NULL,2,1,2),(5,'test','test','test','test','2022-06-22 12:00:00','2022-06-22 12:30:00',NULL,NULL,NULL,NULL,2,1,2),(6,'test','test','test','test','2022-06-22 12:00:00','2022-06-22 12:30:00',NULL,NULL,NULL,NULL,2,1,2),(7,'test','test','test','test','2022-06-22 11:30:00','2022-06-22 12:00:00',NULL,NULL,NULL,NULL,2,1,2),(8,'test','test','test','test','2022-06-22 15:30:00','2022-06-22 16:00:00',NULL,NULL,NULL,NULL,2,1,1),(9,'test','test','test','test','2022-06-22 13:00:00','2022-06-22 13:30:00',NULL,NULL,NULL,NULL,2,1,2),(10,'test','test','test','test','2022-06-23 01:30:00','2022-06-23 02:00:00',NULL,NULL,NULL,NULL,2,1,2),(11,'test','test','test','test','2022-06-23 12:00:00','2022-06-23 12:30:00',NULL,NULL,NULL,NULL,2,1,1),(12,'test','test','test','test','2022-06-23 13:00:00','2022-06-23 13:30:00',NULL,NULL,NULL,NULL,2,1,1),(13,'test','test','test','test','2022-06-24 15:00:00','2022-06-24 15:30:00',NULL,NULL,NULL,NULL,2,1,2);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `Contact_ID` int NOT NULL AUTO_INCREMENT,
  `Contact_Name` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Contact_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'Anika Costa','acoasta@company.com'),(2,'Daniel Garcia','dgarcia@company.com'),(3,'Li Lee','llee@company.com');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `Country_ID` int NOT NULL AUTO_INCREMENT,
  `Country` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Country_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'U.S','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script'),(2,'UK','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script'),(3,'Canada','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `Customer_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_Name` varchar(50) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Postal_Code` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Division_ID` int NOT NULL,
  PRIMARY KEY (`Customer_ID`),
  KEY `fk_division_id_idx` (`Division_ID`),
  CONSTRAINT `fk_division_id` FOREIGN KEY (`Division_ID`) REFERENCES `first_level_divisions` (`Division_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (2,'Lady McAnderson','2 Wonder Way','AF19B','11-445-910-2135','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',103),(3,'Dudley Do-Right','48 Horse Manor ','28198','874-916-2671','2022-06-10 14:50:04','script','2022-06-10 14:50:04','script',60),(5,'Megan Riffey2','7 E Junction St','46154','3175034312','2022-06-15 23:38:20',NULL,'2022-06-15 23:38:20',NULL,1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_level_divisions`
--

DROP TABLE IF EXISTS `first_level_divisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `first_level_divisions` (
  `Division_ID` int NOT NULL AUTO_INCREMENT,
  `Division` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Country_ID` int NOT NULL,
  PRIMARY KEY (`Division_ID`),
  KEY `fk_country_id_idx` (`Country_ID`),
  CONSTRAINT `fk_country_id` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`Country_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3979 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_level_divisions`
--

LOCK TABLES `first_level_divisions` WRITE;
/*!40000 ALTER TABLE `first_level_divisions` DISABLE KEYS */;
INSERT INTO `first_level_divisions` VALUES (1,'Alabama','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(2,'Arizona','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(3,'Arkansas','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(4,'California','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(5,'Colorado','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(6,'Connecticut','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(7,'Delaware','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(8,'District of Columbia','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(9,'Florida','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(10,'Georgia','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(11,'Idaho','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(12,'Illinois','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(13,'Indiana','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(14,'Iowa','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(15,'Kansas','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(16,'Kentucky','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(17,'Louisiana','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(18,'Maine','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(19,'Maryland','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(20,'Massachusetts','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(21,'Michigan','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(22,'Minnesota','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(23,'Mississippi','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(24,'Missouri','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(25,'Montana','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(26,'Nebraska','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(27,'Nevada','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(28,'New Hampshire','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(29,'New Jersey','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(30,'New Mexico','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(31,'New York','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(32,'North Carolina','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(33,'North Dakota','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(34,'Ohio','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(35,'Oklahoma','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(36,'Oregon','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(37,'Pennsylvania','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(38,'Rhode Island','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(39,'South Carolina','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(40,'South Dakota','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(41,'Tennessee','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(42,'Texas','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(43,'Utah','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(44,'Vermont','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(45,'Virginia','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(46,'Washington','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(47,'West Virginia','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(48,'Wisconsin','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(49,'Wyoming','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',1),(52,'Hawaii','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(54,'Alaska','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script',1),(60,'Northwest Territories','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(61,'Alberta','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(62,'British Columbia','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(63,'Manitoba','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(64,'New Brunswick','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(65,'Nova Scotia','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(66,'Prince Edward Island','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(67,'Ontario','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(68,'Québec','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(69,'Saskatchewan','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(70,'Nunavut','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(71,'Yukon','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(72,'Newfoundland and Labrador','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',3),(101,'England','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',2),(102,'Wales','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',2),(103,'Scotland','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',2),(104,'Northern Ireland','2021-09-10 13:14:10','script','2021-09-10 17:14:10','script',2);
/*!40000 ALTER TABLE `first_level_divisions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(50) DEFAULT NULL,
  `Password` text,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_Name_UNIQUE` (`User_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test','test','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script'),(2,'admin','admin','2021-09-10 13:14:09','script','2021-09-10 17:14:09','script');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24 13:06:31
