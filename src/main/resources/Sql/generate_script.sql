CREATE DATABASE forza4;
USE forza4;
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) NOT NULL,
  auth_token varchar(255) DEFAULT NULL,
  email_confirmed tinyint(1) DEFAULT NULL,
  email_token varchar(255) DEFAULT NULL,
  salt varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username),
  UNIQUE KEY email (email)
);
CREATE TABLE stats (
  id int(11) NOT NULL AUTO_INCREMENT,
  userId int(11) NOT NULL,
  games int(11) DEFAULT NULL,
  wins int(11) DEFAULT NULL,
  ties int(11) DEFAULT NULL,
  defeats int(11) DEFAULT NULL,
  points int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES users(id)
);


