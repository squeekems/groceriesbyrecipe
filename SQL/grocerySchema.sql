CREATE DATABASE IF NOT EXISTS `grocery`
USE `grocery`;

CREATE TABLE IF NOT EXISTS `aisles` (
  `aisleID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`aisleID`),
  KEY `FKga73pc06k2s9og9oh65pj9cef` (`userID`),
  CONSTRAINT `FKga73pc06k2s9og9oh65pj9cef` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `amounts` (
  `amountID` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`amountID`),
  KEY `FKecr1hvwcbl91w9l29nqqtw5ki` (`userID`),
  CONSTRAINT `FKecr1hvwcbl91w9l29nqqtw5ki` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `ingredients` (
  `ingredientID` bigint(20) NOT NULL AUTO_INCREMENT,
  `amountID` bigint(20) DEFAULT NULL,
  `itemID` bigint(20) DEFAULT NULL,
  `unitOfMeasurementID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ingredientID`),
  KEY `FKa8pix1h5jploejrcy72x5ng9q` (`amountID`),
  KEY `FKliovtm3hde4feissn32xyi7p4` (`itemID`),
  KEY `FKd3i0hr593sh81f69y8wy0dr64` (`unitOfMeasurementID`),
  CONSTRAINT `FKa8pix1h5jploejrcy72x5ng9q` FOREIGN KEY (`amountID`) REFERENCES `amounts` (`amountID`),
  CONSTRAINT `FKd3i0hr593sh81f69y8wy0dr64` FOREIGN KEY (`unitOfMeasurementID`) REFERENCES `unitsofmeasurement` (`unitOfMeasurementID`),
  CONSTRAINT `FKliovtm3hde4feissn32xyi7p4` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `items` (
  `itemID` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `aisleID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`itemID`),
  KEY `FKkirstv0gg2eesfnfk9heisa0f` (`aisleID`),
  KEY `FKb17ea3xb6lnd115geal1xmty4` (`userID`),
  CONSTRAINT `FKb17ea3xb6lnd115geal1xmty4` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `FKkirstv0gg2eesfnfk9heisa0f` FOREIGN KEY (`aisleID`) REFERENCES `aisles` (`aisleID`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `recipes` (
  `recipeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `instructions` varchar(2055) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`recipeID`),
  KEY `FK4lnc2ukf71ehk516ikxxfr76c` (`userID`),
  CONSTRAINT `FK4lnc2ukf71ehk516ikxxfr76c` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `recipes_ingredients` (
  `recipeID` bigint(20) NOT NULL,
  `ingredientID` bigint(20) NOT NULL,
  PRIMARY KEY (`recipeID`,`ingredientID`),
  KEY `FKonkrlktiart1hc9c4n1f1aohj` (`ingredientID`),
  CONSTRAINT `FKdjt1abvu5ym372rkaam00hg00` FOREIGN KEY (`recipeID`) REFERENCES `recipes` (`recipeID`),
  CONSTRAINT `FKonkrlktiart1hc9c4n1f1aohj` FOREIGN KEY (`ingredientID`) REFERENCES `ingredients` (`ingredientID`)
) ENGINE=InnoDB DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `shoppinglist` (
  `shoppingListID` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `itemID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`shoppingListID`),
  KEY `FKqj1q2jq7jbg1oiyg8luc9gb9n` (`itemID`),
  KEY `FKgk459e1l9pfaxhe1s4ggw27g8` (`userID`),
  CONSTRAINT `FKgk459e1l9pfaxhe1s4ggw27g8` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `FKqj1q2jq7jbg1oiyg8luc9gb9n` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `unitsofmeasurement` (
  `unitOfMeasurementID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`unitOfMeasurementID`),
  KEY `FK3qc21pa6jstp3t4juahegmyjx` (`userID`),
  CONSTRAINT `FK3qc21pa6jstp3t4juahegmyjx` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=LATIN1;

CREATE TABLE IF NOT EXISTS `users` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=LATIN1;

