CREATE DATABASE `genesys_gym` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `teacheable` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

CREATE TABLE `exercise_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `id_activity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_exercisetype_idx` (`id_activity`),
  CONSTRAINT `activity_exercisetype` FOREIGN KEY (`id_activity`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `fee` (
  `dni_people` int NOT NULL,
  `due_date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `pay_date` date DEFAULT NULL,
  PRIMARY KEY (`dni_people`,`due_date`),
  CONSTRAINT `people_rate` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `people_class` (
  `dni_people` int NOT NULL,
  `id_class` int NOT NULL,
  PRIMARY KEY (`dni_people`,`id_class`),
  KEY `class_peopleclass_idx` (`id_class`),
  CONSTRAINT `class_peopleclass` FOREIGN KEY (`id_class`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `people_peopleclass` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plan_activity` (
  `id_plan` int NOT NULL,
  `id_activity` int NOT NULL,
  PRIMARY KEY (`id_plan`,`id_activity`),
  KEY `activity_planactivity_idx` (`id_activity`),
  CONSTRAINT `activity_planactivity` FOREIGN KEY (`id_activity`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plan_planactivity` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `routine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `dni_people` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routine_people_fk_idx` (`dni_people`),
  CONSTRAINT `routine_people_fk` FOREIGN KEY (`dni_people`) REFERENCES `people` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;