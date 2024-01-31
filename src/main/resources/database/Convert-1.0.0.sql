CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `internal_code` varchar(20) NOT NULL UNIQUE,
  `edit_date_time` datetime DEFAULT NULL,
  `front` varchar(1024) DEFAULT NULL,
  `back` varchar(1024) DEFAULT NULL,
  `example` varchar(1024) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3