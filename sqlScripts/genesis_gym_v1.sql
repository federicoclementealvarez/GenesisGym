-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: genesys_gym
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `teacheable` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Musculacion','Incluye todas las máquinas relacionadas con los ejercicios de musculación',0),(2,'Pilates','Incluye a las clases de pilates dictadas por un profesor del gimnasio',1),(5,'Zumba','Incluye a las clases de zumba dictadas por un profesor del gimnasio',1),(6,'Boxeo','Incluye a las clases de boxeo dictadas por un profesor del gimnasio',1),(7,'Musculacion pro','Incluye todas las máquinas relacionadas con los ejercicios de musculación pro',0);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` varchar(45) NOT NULL,
  `begin_hour` time NOT NULL,
  `end_hour` time NOT NULL,
  `dni_teacher` int NOT NULL,
  `id_activity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_class_idx` (`dni_teacher`),
  KEY `activity_class_idx` (`id_activity`),
  CONSTRAINT `activity_class` FOREIGN KEY (`id_activity`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_class` FOREIGN KEY (`dni_teacher`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'martes y jueves','19:30:00','20:30:00',41551974,2),(2,'miécoles y viernes','17:00:00','18:00:00',41551974,2),(6,'Martes','19:30:00','20:30:00',41222111,6),(7,'S�bado','10:30:00','11:30:00',41222111,5);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_routine` int NOT NULL,
  `id_exercise_type` int NOT NULL,
  `set_quantity` int NOT NULL,
  `repetition_quantity` int NOT NULL,
  `rest_time_between_sets` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exercise_exercise_type_idx` (`id_exercise_type`),
  KEY `exercise_routine_fk_idx` (`id_routine`),
  CONSTRAINT `exercise_exercise_type` FOREIGN KEY (`id_exercise_type`) REFERENCES `exercise_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `exercise_routine_fk` FOREIGN KEY (`id_routine`) REFERENCES `routine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (2,1,1,3,3,'00:00:09'),(3,1,2,9,5,'00:01:05'),(39,20,2,5,25,'00:00:20'),(54,21,1,3,2,'00:00:14'),(55,21,2,3,2,'00:00:19'),(56,11,1,2,3,'00:00:10'),(57,11,9,3,2,'00:00:15');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_type`
--

DROP TABLE IF EXISTS `exercise_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `id_activity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_exercisetype_idx` (`id_activity`),
  CONSTRAINT `activity_exercisetype` FOREIGN KEY (`id_activity`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_type`
--

LOCK TABLES `exercise_type` WRITE;
/*!40000 ALTER TABLE `exercise_type` DISABLE KEYS */;
INSERT INTO `exercise_type` VALUES (1,'Press-banca','El levantador se tumba sobre su espalda en un banco, levantando y bajando la barra directamente por encima del pecho',1),(2,'Levantamiento de peso muerto','El levantador eleva una barra con peso desde el suelo hasta la cintura en un movimiento fluido y compacto',1),(5,'Dominadas','El atleta flexiona rodillas y caderas, bajando y subiendo con barra sobre hombros',1),(6,'Curl de biceps','Con barra o mancuernas, se flexionan codos elevando el peso hacia hombros',1),(7,'Elevaciones laterales','Con mancuernas, se elevan brazos lateralmente hasta la altura de los hombros',1),(8,'Remo','Sentado, se tracciona agarre hacia el torso activando espalda media y posterior',7),(9,'Cruce de poleas','De pie, se aproximan brazos desde poleas laterales enfocando el trabajo en pecho',7);
/*!40000 ALTER TABLE `exercise_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `dni_people` int NOT NULL,
  `due_date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `pay_date` date DEFAULT NULL,
  PRIMARY KEY (`dni_people`,`due_date`),
  CONSTRAINT `people_rate` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (41551974,'2026-05-31',0.00,NULL),(44232733,'2026-03-31',65.00,NULL),(44232733,'2026-04-30',65.00,NULL),(44232733,'2026-05-31',65.00,NULL),(44232735,'2026-05-31',100.00,NULL);
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `people` (
  `dni` int NOT NULL,
  `name` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,
  `birthdate` date NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone_number` varchar(25) NOT NULL,
  `type` varchar(15) NOT NULL,
  `id_plan` int NOT NULL,
  `dni_teacher` int DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `client_teacher_idx` (`dni_teacher`),
  KEY `plan_people_idx` (`id_plan`),
  CONSTRAINT `client_teacher` FOREIGN KEY (`dni_teacher`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plan_people` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (41111222,'Julián','Lopez','2023-09-13','pass123','3413332222','client',1,41222111),(41222111,'Marcos','Duke','2002-09-02','pass123','3413226969','teacher',4,NULL),(41551974,'Juan Cruz','Durán','2002-05-25','pass123','3413335555','teacher',4,NULL),(44232733,'Federico','Alvarez','2002-08-24','pass123','3413334444','client',3,41551974),(44232734,'Fede','Admin','2002-08-24','pass123','3413334444','admin',4,NULL),(44232735,'Juan','Perez','2005-01-07','pass123','3412225656','client',1,41551974);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_class`
--

DROP TABLE IF EXISTS `people_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `people_class` (
  `dni_people` int NOT NULL,
  `id_class` int NOT NULL,
  PRIMARY KEY (`dni_people`,`id_class`),
  KEY `class_peopleclass_idx` (`id_class`),
  CONSTRAINT `class_peopleclass` FOREIGN KEY (`id_class`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `people_peopleclass` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_class`
--

LOCK TABLES `people_class` WRITE;
/*!40000 ALTER TABLE `people_class` DISABLE KEYS */;
INSERT INTO `people_class` VALUES (44232733,1),(44232735,1),(41551974,2),(44232733,2);
/*!40000 ALTER TABLE `people_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'Premium',1500.00),(3,'Cardio',650.00),(4,'Profesor',0.00),(5,'Musculacion',500.00),(6,'Musculacion pro',1000.00),(7,'Boxeo',250.00);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_activity`
--

DROP TABLE IF EXISTS `plan_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_activity` (
  `id_plan` int NOT NULL,
  `id_activity` int NOT NULL,
  PRIMARY KEY (`id_plan`,`id_activity`),
  KEY `activity_planactivity_idx` (`id_activity`),
  CONSTRAINT `activity_planactivity` FOREIGN KEY (`id_activity`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plan_planactivity` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_activity`
--

LOCK TABLES `plan_activity` WRITE;
/*!40000 ALTER TABLE `plan_activity` DISABLE KEYS */;
INSERT INTO `plan_activity` VALUES (1,1),(4,1),(5,1),(6,1),(1,2),(3,2),(4,2),(1,5),(3,5),(1,6),(7,6),(1,7),(6,7);
/*!40000 ALTER TABLE `plan_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routine`
--

DROP TABLE IF EXISTS `routine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `dni_people` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routine_people_fk_idx` (`dni_people`),
  CONSTRAINT `routine_people_fk` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routine`
--

LOCK TABLES `routine` WRITE;
/*!40000 ALTER TABLE `routine` DISABLE KEYS */;
INSERT INTO `routine` VALUES (1,'Mi rutina diaria','Personal',41111222),(11,'Rutina telefono','Personal',41111222),(20,'rutina 1','Personal',41551974),(21,'aaa','Personal',41551974),(23,'rutina 1','Personal',44232733);
/*!40000 ALTER TABLE `routine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-05  7:31:44
