CREATE DATABASE  IF NOT EXISTS `quiz_mgmt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quiz_mgmt`;
-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: localhost    Database: quiz_mgmt
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ANSWER` varchar(200) NOT NULL,
  `is_correct` bit(1) DEFAULT NULL,
  `QUESTION_ID` int NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_answers_1_idx` (`QUESTION_ID`),
  CONSTRAINT `fk_answers_1` FOREIGN KEY (`QUESTION_ID`) REFERENCES `quiz_question` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'A1',_binary '\0',1,'2024-05-13 12:17:35',NULL),(2,'A2',_binary '\0',1,'2024-05-13 12:17:35',NULL),(3,'A3',_binary '\0',1,'2024-05-13 12:17:35',NULL),(4,'A4',_binary '',1,'2024-05-13 12:17:35',NULL),(5,'A1',_binary '\0',1,'2024-05-13 12:17:35',NULL),(6,'A2',_binary '\0',1,'2024-05-13 12:17:35',NULL),(7,'A3',_binary '\0',1,'2024-05-13 12:17:35',NULL),(8,'A4',_binary '',1,'2024-05-13 12:17:35',NULL),(9,'B1',_binary '\0',2,'2024-05-13 12:17:35',NULL),(10,'B2',_binary '\0',2,'2024-05-13 12:17:35',NULL),(11,'B3',_binary '\0',2,'2024-05-13 12:17:35',NULL),(12,'B4',_binary '',2,'2024-05-13 12:17:35',NULL),(13,'C1',_binary '\0',3,'2024-05-13 12:17:35',NULL),(14,'C2',_binary '\0',3,'2024-05-13 12:17:35',NULL),(15,'C3',_binary '\0',3,'2024-05-13 12:17:35',NULL),(16,'C4',_binary '',3,'2024-05-13 12:17:35',NULL),(17,'D1',_binary '\0',4,'2024-05-13 12:17:35',NULL),(18,'D2',_binary '\0',4,'2024-05-13 12:17:35',NULL),(19,'D3',_binary '\0',4,'2024-05-13 12:17:35',NULL),(20,'D4',_binary '',4,'2024-05-13 12:17:35',NULL),(21,'E1',_binary '\0',5,'2024-05-13 12:17:35',NULL),(22,'E2',_binary '\0',5,'2024-05-13 12:17:35',NULL),(23,'E3',_binary '\0',5,'2024-05-13 12:17:35',NULL),(24,'E4',_binary '',5,'2024-05-13 12:17:35',NULL),(25,'F1',_binary '\0',6,'2024-05-13 12:17:35',NULL),(26,'F2',_binary '\0',6,'2024-05-13 12:17:35',NULL),(27,'F3',_binary '\0',6,'2024-05-13 12:17:35',NULL),(28,'F4',_binary '',6,'2024-05-13 12:17:35',NULL),(29,'G1',_binary '\0',7,'2024-05-13 12:17:35',NULL),(30,'G2',_binary '\0',7,'2024-05-13 12:17:35',NULL),(31,'G3',_binary '\0',7,'2024-05-13 12:17:35',NULL),(32,'G4',_binary '',7,'2024-05-13 12:17:35',NULL),(33,'H1',_binary '\0',8,'2024-05-13 12:17:35',NULL),(34,'H2',_binary '\0',8,'2024-05-13 12:17:35',NULL),(35,'H3',_binary '\0',8,'2024-05-13 12:17:35',NULL),(36,'H4',_binary '',8,'2024-05-13 12:17:35',NULL),(37,'I1',_binary '\0',9,'2024-05-13 12:17:35',NULL),(38,'I2',_binary '\0',9,'2024-05-13 12:17:35',NULL),(39,'I3',_binary '\0',9,'2024-05-13 12:17:35',NULL),(40,'I4',_binary '',9,'2024-05-13 12:17:35',NULL),(41,'J1',_binary '\0',10,'2024-05-13 12:17:35',NULL),(42,'J2',_binary '\0',10,'2024-05-13 12:17:35',NULL),(43,'J3',_binary '\0',10,'2024-05-13 12:17:35',NULL),(44,'J4',_binary '',10,'2024-05-13 12:17:35',NULL),(45,'K1',_binary '\0',11,'2024-05-13 12:17:35',NULL),(46,'K2',_binary '\0',11,'2024-05-13 12:17:35',NULL),(47,'K3',_binary '\0',11,'2024-05-13 12:17:35',NULL),(48,'K4',_binary '',11,'2024-05-13 12:17:35',NULL);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_question`
--

DROP TABLE IF EXISTS `quiz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_question` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `QUESTION` varchar(150) NOT NULL,
  `SUBJECT_ID` int NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_quiz_question_1_idx` (`SUBJECT_ID`),
  CONSTRAINT `fk_quiz_question_1` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
INSERT INTO `quiz_question` VALUES (1,'Q1',1,'2024-05-13 12:17:35',NULL),(2,'Q2',1,'2024-05-13 12:17:35',NULL),(3,'Q24',1,'2024-05-13 12:17:35',NULL),(4,'Q25',1,'2024-05-13 12:17:35',NULL),(5,'Q26',1,'2024-05-13 12:17:35',NULL),(6,'Q3',1,'2024-05-13 12:17:35',NULL),(7,'Q4',1,'2024-05-13 12:17:35',NULL),(8,'Q5',1,'2024-05-13 12:17:35',NULL),(9,'Q6',1,'2024-05-13 12:17:35',NULL),(10,'Q7',1,'2024-05-13 12:17:35',NULL),(11,'Q8',1,'2024-05-13 12:17:35',NULL);
/*!40000 ALTER TABLE `quiz_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(45) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_card`
--

DROP TABLE IF EXISTS `score_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_card` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int NOT NULL,
  `SUBJECT_ID` int NOT NULL,
  `SCORE` int DEFAULT NULL,
  `MAX_SCORE` int NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_score_card_1_idx` (`STUDENT_ID`),
  KEY `fk_score_card_2_idx` (`SUBJECT_ID`),
  CONSTRAINT `fk_score_card_1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_score_card_2` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_card`
--

LOCK TABLES `score_card` WRITE;
/*!40000 ALTER TABLE `score_card` DISABLE KEYS */;
INSERT INTO `score_card` VALUES (1,1,1,2,5,'2024-05-30 11:56:53',NULL),(2,1,1,3,5,'2024-05-30 12:01:13',NULL),(3,2,1,5,5,'2024-05-30 12:01:13',NULL);
/*!40000 ALTER TABLE `score_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SUBJECT_NAME` varchar(45) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Math','2024-05-13 12:17:35',NULL);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `ROLE_ID` int NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_user_role_2_idx` (`USER_ID`),
  KEY `fk_user_role_1_idx` (`ROLE_ID`),
  CONSTRAINT `fk_user_role_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`),
  CONSTRAINT `fk_user_role_2` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `user_name_UNIQUE` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ram','ram_abc','2024-05-13 12:17:35',NULL),(2,'SHYAM','test123','2024-05-13 12:17:35',NULL);
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

-- Dump completed on 2024-05-30 14:51:16
