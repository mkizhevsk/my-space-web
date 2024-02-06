CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,

  PRIMARY KEY (`username`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users`
VALUES
('admin','{noop}586391',1),
('user','{noop}test123',1);

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,

  UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `authorities`
VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_BASIC'),
('user', 'ROLE_BASIC');