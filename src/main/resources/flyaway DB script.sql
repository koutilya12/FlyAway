CREATE DATABASE flyaway;
USE flyaway;
START TRANSACTION;

CREATE TABLE `cities` (
  `cityId` int NOT NULL AUTO_INCREMENT,
  `cityName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `air_lines` (
  `airlineId` int NOT NULL AUTO_INCREMENT,
  `airlineName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`airlineId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) NOT NULL,
  `mobileNum` varchar(10) NOT NULL,
  `password` varchar(25) DEFAULT NULL,
  `type` char(1) NOT NULL,
  `status` char(1) NOT NULL,
  `emailId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName` (`userName`,`mobileNum`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `flight_schedule_details` (
  `flightId` int NOT NULL AUTO_INCREMENT,
  `source` int NOT NULL,
  `destination` int NOT NULL,
  `airlineId` int NOT NULL,
  `price` double NOT NULL,
  `departureTime` datetime NOT NULL,
  `arrivalTime` datetime NOT NULL,
  `capacity` int NOT NULL,
  `filledCapacity` int DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`flightId`),
  KEY `source` (`source`),
  KEY `destination` (`destination`),
  KEY `airlineId` (`airlineId`),
  CONSTRAINT `flight_schedule_details_ibfk_1` FOREIGN KEY (`source`) REFERENCES `cities` (`cityId`),
  CONSTRAINT `flight_schedule_details_ibfk_2` FOREIGN KEY (`destination`) REFERENCES `cities` (`cityId`),
  CONSTRAINT `flight_schedule_details_ibfk_3` FOREIGN KEY (`airlineId`) REFERENCES `air_lines` (`airlineId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `flight_ticket_booking` (
  `bookingId` int NOT NULL AUTO_INCREMENT,
  `bookingTime` datetime NOT NULL,
  `userId` int NOT NULL,
  `flightId` int NOT NULL,
  `noOfPersons` int NOT NULL,
  `totPrice` double NOT NULL,
  `transactionId` varchar(20) DEFAULT NULL,
  `flightBookingStatus` char(1) NOT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `userId` (`userId`),
  KEY `flightId` (`flightId`),
  CONSTRAINT `flight_ticket_booking_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `flight_ticket_booking_ibfk_2` FOREIGN KEY (`flightId`) REFERENCES `flight_schedule_details` (`flightId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `passenger_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookingId` int NOT NULL,
  `passengerName` varchar(30) NOT NULL,
  `gender` char(1) NOT NULL,
  `age` int DEFAULT NULL,
  `mobileNumber` varchar(10) DEFAULT NULL,
  `govDocumentType` char(1) NOT NULL,
  `govDocumentId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `passenger_details_ibfk_1` (`bookingId`),
  CONSTRAINT `passenger_details_ibfk_1` FOREIGN KEY (`bookingId`) REFERENCES `flight_ticket_booking` (`bookingId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;