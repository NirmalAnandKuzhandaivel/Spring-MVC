-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: airlinefinal
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `airline_order`
--

DROP TABLE IF EXISTS `airline_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline_order` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `no_of_passengers` int(11) DEFAULT NULL,
  `Total_price` float DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `FK_o8egn1ighfcvv1tlkndiu61td` (`user_id`),
  CONSTRAINT `FK_o8egn1ighfcvv1tlkndiu61td` FOREIGN KEY (`user_id`) REFERENCES `airline_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_order`
--

LOCK TABLES `airline_order` WRITE;
/*!40000 ALTER TABLE `airline_order` DISABLE KEYS */;
INSERT INTO `airline_order` VALUES (1,1,300,1),(2,1,800,1),(3,1,560,1),(4,1,345,1),(5,1,560,4),(6,1,567,1),(7,1,45,1),(8,1,300,1),(9,2,1824,1),(10,1,560,1),(11,1,300,2),(12,1,800,1);
/*!40000 ALTER TABLE `airline_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline_passenger`
--

DROP TABLE IF EXISTS `airline_passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline_passenger` (
  `PassengerID` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `id_proof` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `ORDER_ID` int(11) NOT NULL,
  PRIMARY KEY (`PassengerID`),
  KEY `FK_2f47j8nm5crb0qobo8956xkxw` (`ORDER_ID`),
  CONSTRAINT `FK_2f47j8nm5crb0qobo8956xkxw` FOREIGN KEY (`ORDER_ID`) REFERENCES `airline_order` (`ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_passenger`
--

LOCK TABLES `airline_passenger` WRITE;
/*!40000 ALTER TABLE `airline_passenger` DISABLE KEYS */;
INSERT INTO `airline_passenger` VALUES (1,24,'NirmalAnand','AJXPN6618H','Kuzhandaivel',1),(2,67,'Lakshmi','AJXPN6618H','Narayana',2),(3,45,'hari','AJXPN6618H','krishnan',3),(4,56,'NirmalAnand','AJXPN6618H','Kuzhandaivel',4),(5,23,'support','support','support',5),(6,23,'NirmalAnand','AJXPN6618H','Kuzhandaivel',6),(7,23,'raghu','AJXPN6618H','raghu',7),(8,23,'raghu','AJXPN6618H','raghu',8),(9,23,'Ayushi','AJXPN6618H','Mansi',9),(10,23,'Sreeni','AJXPN9900K','Lakshmi',9),(11,23,'NirmalAnand','AJXPN6618H','Kuzhandaivel',10),(12,23,'Ayushi','AJXPN6618H','ayu',11),(13,23,'NirmalAnand','AJXPN6618H','Kuzhandaivel',12);
/*!40000 ALTER TABLE `airline_passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline_tickets`
--

DROP TABLE IF EXISTS `airline_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline_tickets` (
  `TICKET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `endDate` date NOT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `flight_Company` varchar(255) DEFAULT NULL,
  `FLIGHT_ID` varchar(255) NOT NULL,
  `from_place` varchar(255) DEFAULT NULL,
  `journeyHours` varchar(255) DEFAULT NULL,
  `ticket_price` float DEFAULT NULL,
  `startDate` date NOT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `ticketType` varchar(255) DEFAULT NULL,
  `to_place` varchar(255) DEFAULT NULL,
  `ORDER_ID` int(11) NOT NULL,
  PRIMARY KEY (`TICKET_ID`),
  KEY `FK_as70onitrkhm8tdxhmk928sfx` (`ORDER_ID`),
  CONSTRAINT `FK_as70onitrkhm8tdxhmk928sfx` FOREIGN KEY (`ORDER_ID`) REFERENCES `airline_order` (`ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_tickets`
--

LOCK TABLES `airline_tickets` WRITE;
/*!40000 ALTER TABLE `airline_tickets` DISABLE KEYS */;
INSERT INTO `airline_tickets` VALUES (1,'2016-04-27','3:00am','British','101','chennai',NULL,300,'2016-04-26','2:00am','DEPARTURE','boston',1),(2,'2016-05-01','3:00pm','British','103','boston',NULL,500,'2016-04-30','4:00am','RETURN','chennai',2),(3,'2016-04-27','3:00am','British','101','chennai',NULL,300,'2016-04-26','2:00am','DEPARTURE','boston',2),(4,'2016-04-27','11:00pm','British','102','chennai',NULL,560,'2016-04-26','4:00am','DEPARTURE','boston',3),(5,'2016-04-29','4:00am','qatar','105','bangalore',NULL,345,'2016-04-27','5:00am','DEPARTURE','chicago',4),(6,'2016-04-27','11:00pm','British','102','chennai',NULL,560,'2016-04-26','4:00am','DEPARTURE','boston',5),(7,'2016-05-07','1:00pm','Emirates','106','chicago',NULL,567,'2016-05-06','2:00am','DEPARTURE','bangalore',6),(8,'2016-04-28','2:00am','Emirates','107','chennai',NULL,45,'2016-04-27','2:00am','DEPARTURE','singapore',7),(9,'2016-04-27','3:00am','British','101','chennai',NULL,300,'2016-04-26','2:00am','DEPARTURE','boston',8),(10,'2016-05-07','1:00pm','Emirates','106','chicago',NULL,1134,'2016-05-06','2:00am','RETURN','bangalore',9),(11,'2016-04-29','4:00am','qatar','105','bangalore',NULL,690,'2016-04-27','5:00am','DEPARTURE','chicago',9),(12,'2016-04-27','11:00pm','British','102','chennai',NULL,560,'2016-04-26','4:00am','DEPARTURE','boston',10),(13,'2016-04-27','3:00am','British','101','chennai',NULL,300,'2016-04-26','2:00am','DEPARTURE','boston',11),(14,'2016-05-01','3:00pm','British','103','boston',NULL,500,'2016-04-30','4:00am','RETURN','chennai',12),(15,'2016-04-27','3:00am','British','101','chennai',NULL,300,'2016-04-26','2:00am','DEPARTURE','boston',12);
/*!40000 ALTER TABLE `airline_tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline_users`
--

DROP TABLE IF EXISTS `airline_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photoPath_Name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_users`
--

LOCK TABLES `airline_users` WRITE;
/*!40000 ALTER TABLE `airline_users` DISABLE KEYS */;
INSERT INTO `airline_users` VALUES (1,'kuzhandaivel.n@husky.neu.edu','NirmalAnand','Kuzhandaivel','Summer@123','/airline/resources/images/1461670480741DSC_5059.JPG','CUSTOMER'),(2,'admin','admin','admin','admin','/airline/resources/images/1461664078937nirmal.jpg','ADMIN'),(3,'nik.tom10@gmail.com','nikil','issac','Summer@123',NULL,'SUPPORT'),(4,'support@gmail.com','support','support','Summer@123',NULL,'SUPPORT'),(5,'nimi.hbk@gmail.com','NirmalAnand','Kuzhandaivel','Summer@123',NULL,'SUPPORT'),(8,'harishaseme@gmail.com','Harish','Gunasekaran','Summer@123',NULL,'CUSTOMER'),(9,'munch.deepu@gmail.com','deepika','devi','Summer@123',NULL,'CUSTOMER');
/*!40000 ALTER TABLE `airline_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_details`
--

DROP TABLE IF EXISTS `flight_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_details` (
  `FLIGHT_ID` varchar(255) NOT NULL,
  `endDate` date NOT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `flight_Company` varchar(255) DEFAULT NULL,
  `from_place` varchar(255) DEFAULT NULL,
  `journey_hours` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `startDate` date NOT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `ticketAvailability` int(11) DEFAULT NULL,
  `to_place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FLIGHT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_details`
--

LOCK TABLES `flight_details` WRITE;
/*!40000 ALTER TABLE `flight_details` DISABLE KEYS */;
INSERT INTO `flight_details` VALUES ('101','2016-04-27','3:00am','British','chennai','25 hours',300,'2016-04-26','2:00am',42,'boston'),('102','2016-04-27','11:00pm','British','chennai','43 hours',560,'2016-04-26','4:00am',42,'boston'),('103','2016-05-01','3:00pm','British','boston','35 hours',500,'2016-04-30','4:00am',34,'chennai'),('104','2016-04-30','11:00pm','Qatar','boston','45 hours',450,'2016-04-29','2:00am',56,'chennai'),('105','2016-04-29','4:00am','qatar','bangalore','49 hours',345,'2016-04-27','5:00am',55,'chicago'),('106','2016-05-07','1:00pm','Emirates','chicago','35 hours',567,'2016-05-06','2:00am',88,'bangalore'),('107','2016-04-28','2:00am','Emirates','chennai','24 hours',45,'2016-04-27','2:00am',66,'singapore'),('108','2016-04-27','1:00am','Emirates','chennai','25 hours',34,'2016-04-26','2:00am',34,'bangalore'),('109','2016-04-28','1:00am','Emirates','bangalore','25 hours',45,'2016-04-27','2:00am',34,'chennai'),('110','2016-05-13','8:00am','SGAirlines','bangalore','30 hours',890,'2016-05-12','2:00am',24,'Singapore'),('111','2016-05-14','8:00am','SGAirlines','Singapore','30 hours',456,'2016-05-13','2:00am',96,'bangalore'),('112','2016-05-08','11:00pm','AirIndia','chennai','45 hours',1000,'2016-05-07','2:00am',34,'london'),('113','2016-05-10','11:00pm','AirIndia','london','45 hours',567,'2016-05-09','2:00am',45,'chennai');
/*!40000 ALTER TABLE `flight_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `MESSAGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `message_Header` varchar(255) DEFAULT NULL,
  `message_Text` varchar(255) DEFAULT NULL,
  `message_Type` varchar(255) DEFAULT NULL,
  `REFERENCE_ID` bigint(20) DEFAULT NULL,
  `response_status` int(11) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Request assistance','Assistance Required','USER REQUEST',8612371875763393410,2,'kuzhandaivel.n@husky.neu.edu'),(2,'Response for8612371875763393410','reponse added','SUPPORT RESPONSE',8612371875763393410,2,'nimi.hbk@gmail.com'),(3,'send me response','dont send me response','USER REQUEST',2081642903631605971,2,'kuzhandaivel.n@husky.neu.edu'),(4,'Response for2081642903631605971','Response sent','SUPPORT RESPONSE',2081642903631605971,2,'nimi.hbk@gmail.com'),(5,'hi ','how are u','USER REQUEST',3827695338547868319,1,'kuzhandaivel.n@husky.neu.edu');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'airlinefinal'
--

--
-- Dumping routines for database 'airlinefinal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  8:25:22
