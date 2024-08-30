CREATE TABLE deck (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `internal_code` varchar(20) NOT NULL UNIQUE,
  `edit_date_time` datetime DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `deleted` tinyint(1) DEFAULT false,
  PRIMARY KEY (`id`),
  KEY `FK_DECK_USER_NAME_idx` (`username`),
  CONSTRAINT `FK_DECK_USER_NAME` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

ALTER TABLE `card` ADD COLUMN `deck_id` int AFTER `id`;
ALTER TABLE `card` ADD KEY `FK_CARD_DECK_idx` (`deck_id`);
ALTER TABLE `card` ADD CONSTRAINT `FK_CARD_DECK` FOREIGN KEY (`deck_id`) REFERENCES `deck` (`id`);
