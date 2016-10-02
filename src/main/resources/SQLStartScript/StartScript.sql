DROP DATABASE IF EXISTS profiles_db;

CREATE DATABASE IF NOT EXISTS profiles_db;

USE profiles_db;

CREATE TABLE profiles_db.profile
(
  profile_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  firstName   VARCHAR(30),
  lastName    VARCHAR(30),
  birthDate   DATE,
  email       VARCHAR(30) UNIQUE ,
  age         INT(30) DEFAULT NULL,
  sex        VARCHAR(30),
  city        VARCHAR(30),
  phoneNumber VARCHAR(30)
);

CREATE TABLE profiles_db.users
(
  user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL UNIQUE,
  login VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  profile_id INT NOT NULL,
  FOREIGN KEY (profile_id) REFERENCES profile (profile_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE  post (
  post_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  text VARCHAR(1024),
  post_date DATETIME NOT NULL,
  photo LONGBLOB,
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);




