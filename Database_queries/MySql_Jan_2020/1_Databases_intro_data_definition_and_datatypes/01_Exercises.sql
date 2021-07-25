#EX 01
CREATE TABLE `minions`(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50),
    age INT NOT NULL CHECK(age >= 0 AND age < 130)
);

CREATE TABLE `towns`(
	town_id INT PRIMARY KEY auto_increment,
    name VARCHAR(50)
);

#EX 02
ALTER TABLE `minions`
ADD COLUMN `town_id` INT;

ALTER TABLE `minions`
ADD CONSTRAINT `fk_id`
FOREIGN KEY (town_id) REFERENCES towns(id);

#EX 03
INSERT INTO minions(`id`, `name`, age, town_id)
VALUES  (1, "Kevin", 22, 1), (2, "Bob", 15, 3), (3, "Steward", NULL, 2);

INSERT INTO towns(`id`, `name`)
VALUES  (1, "Sofia"), (2, "Plovdiv"), (3, "Varna");

#Ex 04
TRUNCATE `minions`;

#Ex 05
DROP TABLES minions, towns;

#Ex 06
CREATE TABLE `people` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` MEDIUMBLOB,
    `height` FLOAT(2),
    `weight` FLOAT(2),
    `gender` CHAR(1) CHECK (`gender` = 'm' OR `gender` = 'f') NOT NULL,
	`birthdate` DATE NOT NULL,
    `biography` TEXT 
);

INSERT INTO `people`(`name`, picture, height, weight, gender, birthdate, biography)
VALUES 
	("Pesho", NULL, 166.22, 70.29, 'm', "1994-02-02", NULL),
	("Ginka", NULL, 163.43, 73.29, 'f', "1992-04-14", NULL),
	("Joro", NULL, 185.77, 77.29, 'm', "1998-02-07", NULL),
	("Mitko", NULL, 181.26, 80.29, 'm', "1990-09-12", NULL),
	("Penka", NULL, 161.92, 57.29, 'f', "1991-11-22", NULL);

SELECT * FROM people;

#Ex 07
CREATE TABLE `users`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL, 
    `password` VARCHAR(26) NOT NULL,
	`profile_picture` MEDIUMBLOB,
    `last_login_time` TIMESTAMP,
    `is_deleted` BOOLEAN NOT NULL
);

INSERT INTO `users`(`username`, `password`, `profile_picture`, 
`last_login_time`, `is_deleted`)
VALUES ("Terminator123", "12345", NULL, now(), false),
("Terminator123", "12345", NULL, now(), false),
("Kasld1", "12345", NULL, now(), false),
("GOsho5", "12345", NULL, now(), false),
("Manyakaa", "12345", NULL, now(), false);

SELECT * FROM `users`;

#Ex 08
# ALTER TABLE `users` ADD INDEX(`id`); 
# Might be a good idea to use if not set up previously
ALTER TABLE `users`
DROP PRIMARY KEY;

ALTER TABLE `users`
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`, `username`);

#Ex 09
ALTER TABLE `users`
MODIFY COLUMN `last_login_time` TIMESTAMP DEFAULT NOW();

#Ex 10
ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
ADD CONSTRAINT UNIQUE (`username`);

#Ex 11
CREATE DATABASE `Movies`;
Use `Movies`;

CREATE TABLE `directors`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `director_name` VARCHAR(100) NOT NULL,
    `notes` TEXT DEFAULT NULL
);

CREATE TABLE `genres`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `genre_name` VARCHAR(30) NOT NULL,
    `notes` TEXT DEFAULT NULL
);

CREATE TABLE `categories`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category_name` VARCHAR(30) NOT NULL,
    `notes` TEXT DEFAULT NULL
);

CREATE TABLE `movies`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR (50) NOT NULL,
    `director_id` INT,
    `copyright_year` YEAR NOT NULL,
    `genre_id` INT,
    `category_id` INT,
    `rating` DOUBLE(4, 2) DEFAULT 0.00,
    `notes` TEXT DEFAULT NULL,
    CONSTRAINT `fk_movies_directors`
    FOREIGN KEY (`director_id`) REFERENCES `directors`(`id`),
    CONSTRAINT `fk_movies_genres`
    FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`),
    CONSTRAINT `fk_movies_categories`
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

INSERT INTO `directors` (`director_name`)
VALUES ("Steven Spielberg"),
("Quentin Tarantino"),
("M. Night Shyamalan"),
("John Smith"),
("Francis Ford Copolla");

INSERT INTO `genres`(`genre_name`)
VALUES("Thriller"),
("Drama"),
("Comedy"),
("Action"),
("Sci-Fi");

INSERT INTO `categories`(`category_name`)
VALUES ("U"),
("PG"),
("12"),
("15"),
("18");

INSERT INTO `movies` (`title`, `director_id`, `copyright_year`, `genre_id`, `category_id`, `rating`)
VALUES("War of the Worlds", 1, "2005", 5, 2, 6.5),
("Once Upon A Time in Hollywood", 2, "2019", 2, 5, 7.6),
("After Earth", 3, "2013", 4, 4, 4.8),
("The Great Walk", 4, "2001", 3, 1, 5.2),
("The Godfather", 5, "1972", 2, 5, 9.2);

#Ex 12
CREATE DATABASE `car_rental`;

CREATE TABLE `categories`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(30) NOT NULL,
    `daily_rate` DECIMAL(6,2) NOT NULL,
    `weekly_rate` DECIMAL(6,2) NOT NULL,
    `monthly_rate` DECIMAL(6,2) NOT NULL,
    `weekend_rate` DECIMAL(6,2) NOT NULL
);

CREATE TABLE `cars`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `plate_number` VARCHAR(30) NOT NULL UNIQUE,
    `make` VARCHAR(30) NOT NULL,
    `model` VARCHAR(30) DEFAULT "unavailable",
    `car_year` YEAR DEFAULT (YEAR(curdate())),
    `category_id` INT NOT NULL,
    `doors` INT,
    `picture` MEDIUMBLOB,
    `car_condition` VARCHAR(30),
    `available` BOOLEAN NOT NULL,
    CONSTRAINT `fk_cars_categories`
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `title` VARCHAR(30) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `customers`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `driver_licence_number` BIGINT NOT NULL,
    `full_name` VARCHAR(200) NOT NULL,
    `address` VARCHAR(200),
    `city` VARCHAR(55),
    `zip_code` INT,
    `notes` TEXT
);

CREATE TABLE `rental_orders`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `car_condition` VARCHAR(30),
    `tank_level` DECIMAL(6, 2) DEFAULT 100.00,
    `kilometrage_start` DECIMAL(10, 2) NOT NULL,
    `kilometrage_end` DECIMAL(10, 2) NOT NULL,
    `total_kilometrage` DECIMAL(10, 2)  AS (`kilometrage_end` - `kilometrage_start`),
    `start_date` TIMESTAMP NOT NULL,
    `end_date` TIMESTAMP NOT NULL,
    `total_days`INT AS (datediff(`end_date`, `start_date`)),
    `rate_applied` DECIMAL(6,2) NOT NULL,
    `tax_rate` DECIMAL(5, 2)NOT NULL,
    `order_status` VARCHAR(20) NOT NULL,
    `notes` TEXT,
    CONSTRAINT `fk_rental_orders_employees`
    FOREIGN KEY (`employee_id`) REFERENCES `employees`(`id`),
    CONSTRAINT `fk_rental_orders_customers`
    FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`),
    CONSTRAINT `fk_rental_orders_cars`
    FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
);

INSERT INTO `categories`(category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
VALUES("sedan", "19.99", "89.99", "629.99", "39.99"),
("SUV", "24.99", "99.99", "729.99", "49.99"),
("convertible", "39.99", "109.99", "859.99", "69.99");

INSERT INTO `cars`(plate_number, make, model, car_year, category_id, doors, picture, car_condition, available)
VALUES("PH1923SM", "BMW", "740", "2021", 1, 4, NULL, "new", TRUE),
("PH1274KI", "Toyota", "RAV4 Hybrid LE", "2020", 2, 4, NULL, "used", FALSE),
("PH5331RA", "Fiat", "500", "2018", 3, 2, NULL, "good", TRUE);

INSERT INTO `employees`(first_name, last_name, title)
VALUES("Stan", "Mon", "Mechanic"),
("Henrich", "Vine", "Manager"),
("Rony", "Boss", "Cashier");

INSERT INTO `customers` (driver_licence_number, full_name, address, city, zip_code)
VALUES(18274723, "Engo Bengo", "Ala bala 24", "Amsterdam", 1234 ),
(18234223, "Mengre Bengo", "Ala bala 25", "Amsterdam", 1231 ),
(18275423, "Afas Bengo", "Ala bala 27", "Amsterdam", 2354 );

INSERT INTO `rental_orders` (employee_id, customer_id, 
car_id, car_condition, tank_level, 
kilometrage_start, kilometrage_end, 
start_date, end_date, rate_applied, 
tax_rate, order_status)
VALUES(3, 1, 1, "new", 50.55, 10000.00, 10132.35, "2021-02-02", "2021-02-24", 89.99, 20.50, "completed"),
(3, 3, 1, "new", 77.25, 10132.35, 10253.65, "2021-03-01", "2021-03-15", 89.99, 20.50, "completed"),
(2, 2, 3, "good", 97.00, 56900.00, 78800.44, "2021-05-21", "2021-06-21", 859.99, 20.50, "in progress");

#Ex 13
CREATE TABLE `towns`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE `addresses`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `address_text` VARCHAR(100) NOT NULL,
    `town_id` INT NOT NULL,
    CONSTRAINT `fk_addresses_towns`
    FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE `departments`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE `employees`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `middle_name` VARCHAR(50),
    `last_name` VARCHAR(50) NOT NULL,
    `job_title` VARCHAR(30),
    `department_id` INT,
    `hire_date` DATE,
    `salary` DECIMAL (10, 2),
    `address_id` INT,
    CONSTRAINT `fk_employees_departments`
    FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
    CONSTRAINT `fk_employees_addresses`
    FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

INSERT INTO `towns` (`name`)
VALUES ("Sofia"), ("Plovdiv"), ("Varna"), ("Burgas");

INSERT INTO `departments` (`name`)
VALUES ("Engineering"), 
("Sales"), 
("Marketing"),
("Software Development"), 
("Quality Assurance");

INSERT INTO `employees` (id, first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
VALUES(1, "Ivan",  "Ivanov", "Ivanov", ".NET Developer", 4, "2013-02-01", 3500.00, NULL),
(2, "Petar",  "Petrov", "Petrov", "Senior Engineer", 1, "2004-03-02", 4000.00, NULL),
(3, "Maria",  "Petrova", "Ivanova", "Intern", 5, "2016-08-28", 525.25, NULL),
(4, "Georgi",  "Terziev", "Ivanov", "CEO", 2, "2007-12-09", 3000.00, NULL),
(5, "Peter",  "Pan", "Pan", "Intern", 3, "2016-08-28", 599.88, NULL);

#Ex 14
SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

#Ex 15
SELECT * FROM `towns`
ORDER BY `name`;
SELECT * FROM `departments`
ORDER BY `name`;
SELECT * FROM `employees`
ORDER BY `salary` DESC;

#Ex 16
SELECT `name` FROM `towns`
ORDER BY `name`;
SELECT `name` FROM `departments`
ORDER BY `name`;
SELECT first_name, last_name, job_title, salary FROM `employees`
ORDER BY `salary` DESC;

#Ex 17
UPDATE `employees`
SET `salary` = `salary` * 1.1;

SELECT `salary` FROM `employees`;

#Ex 18
TRUNCATE TABLE `occupancies`;