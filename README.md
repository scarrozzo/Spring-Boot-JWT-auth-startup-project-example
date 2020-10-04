# Spring-Boot-JWT-auth-startup-project-example
 Example project for JWT auth with Spring Boot and Spring Security

# How to use

1) Install MySQL server
2) Create a "jwtexample" database
3) Create the example tables
```sql
CREATE TABLE `custom_user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(100) NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `movie` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(100) NOT NULL,
	`author` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
```
4) Edit application.yml file (path  src/main/resources) with your database details
5) Import the project using your favourite IDE as a maven project
6) Install lombok plugin into your IDE (see: https://www.baeldung.com/lombok-ide)
7) Start JwtexampleApplication with your JDK

# Project info

This project has been tested with these versions:

Spring Boot 2.3.4

Java version 11.0.5

Apache Maven 3.6.3

MySQL server 8.0.18
