# Ex 01

CREATE DATABASE stc;

use stc;

CREATE TABLE addresses(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL
);

CREATE TABLE clients(
	id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE drivers(
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    rating FLOAT DEFAULT 5.5
);

CREATE TABLE cars(
	id INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(20) NOT NULL,
    model VARCHAR(20),
    `year` INT DEFAULT 0 NOT NULL,
    mileage INT DEFAULT 0,
    `condition` CHAR(1) NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_cars_categories
    FOREIGN KEY (category_id)
    REFERENCES categories(id)
 );
 
 CREATE TABLE courses(
	id INT PRIMARY KEY AUTO_INCREMENT,
    from_address_id INT NOT NULL,
    `start` DATETIME NOT NULL,
    car_id INT NOT NULL,
    client_id INT NOT NULL,
	bill DECIMAL(10, 2) DEFAULT 10,
	CONSTRAINT fk_courses_addresses
    FOREIGN KEY (from_address_id)
    REFERENCES addresses(id),
	CONSTRAINT fk_courses_cars
    FOREIGN KEY (car_id)
    REFERENCES cars(id),
    CONSTRAINT fk_courses_clients
    FOREIGN KEY (client_id)
    REFERENCES clients(id)
 );
 
 CREATE TABLE cars_drivers(
	car_id INT NOT NULL,
    driver_id INT NOT NULL,
    CONSTRAINT pk_cars_drivers
    PRIMARY KEY (car_id, driver_id),
    CONSTRAINT fk_cars_drivers_cars
    FOREIGN KEY (car_id)
    REFERENCES cars(id),
    CONSTRAINT fk_cars_drivers_drivers
    FOREIGN KEY (driver_id)
    REFERENCES drivers(id)
 );
 
 # Ex 02
 
 INSERT INTO clients(full_name, phone_number)
 SELECT concat_ws(' ', first_name, last_name) AS full_name, concat('(088) 9999', id * 2) AS phone_number
 FROM drivers
 WHERE id BETWEEN 10 AND 20;
 
 # Ex 03
 
UPDATE cars
SET `condition` = 'C'
WHERE (mileage >= 800000 OR mileage IS NULL)
AND `year` < 2011
AND `make` != 'Mercedes-Benz';
 
 # Ex 04
 
DELETE c FROM clients AS c
LEFT JOIN courses AS co ON c.id = co.client_id
WHERE co.id IS NULL
AND char_length(c.full_name) > 3;

# Ex 05

SELECT `make`, model, `condition`
FROM `cars` AS c
ORDER BY id;

# Ex 06

SELECT d.first_name, d.last_name, c.make, c.model, c.mileage
FROM cars AS c
JOIN cars_drivers AS cd ON c.id = cd.car_id
JOIN drivers AS d ON d.id = cd.driver_id
WHERE mileage IS NOT NULL
ORDER BY mileage DESC, d.first_name;

# Ex 07

SELECT c.id, c.make, c.mileage, COUNT(co.id) AS count_of_courses, ROUND(AVG(bill), 2) AS avg_bill FROM cars AS c
LEFT JOIN courses AS co ON c.id = co.car_id
GROUP BY c.id
HAVING count_of_courses != 2
ORDER BY count_of_courses DESC, c.id;

# Ex 08

SELECT cl.full_name, COUNT(ca.id) AS count_of_cars, SUM(bill) AS total_sum FROM clients AS cl
JOIN courses AS co ON cl.id = co.client_id
JOIN cars AS ca ON co.car_id = ca.id
GROUP BY cl.id
HAVING cl.full_name LIKE '_a%' AND count_of_cars > 1
ORDER BY cl.full_name;

# Ex 09

SELECT ad.`name`, (IF(hour(co.`start`) BETWEEN 6 AND 20, 'Day', 'Night')) AS day_time, co.bill, cl.full_name, ca.make, ca.model, cat.`name` AS category_name
FROM addresses AS ad
JOIN courses AS co ON ad.id = co.from_address_id
JOIN clients AS cl ON co.client_id = cl.id
JOIN cars AS ca ON co.car_id = ca.id
JOIN categories AS cat ON ca.category_id = cat.id
ORDER BY co.id;

# Ex 10

DELIMITER $$

CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20))
RETURNS INT
DETERMINISTIC
BEGIN
DECLARE count_of_courses INT;
	SET count_of_courses := (SELECT t1.count_of_courses FROM (SELECT cl.phone_number, COUNT(co.id) AS count_of_courses 
    FROM clients AS cl
    LEFT JOIN courses AS co ON cl.id = co.client_id
    GROUP BY cl.id
    HAVING cl.phone_number = phone_num) AS t1);
    
	RETURN count_of_courses;
END $$

DELIMITER ;

# Ex 11

## address name, client fullname, bill level, make, condition, name of cat
## order by make, client fullname 

DELIMITER $$

CREATE PROCEDURE udp_courses_by_address(address_name VARCHAR(100))
BEGIN
	SELECT ad.`name`, cl.full_name, (CASE 
		WHEN co.bill < 21 THEN 'Low'
        WHEN co.bill < 31 THEN 'Medium'
        WHEN co.bill > 30 THEN 'High'
    END) AS level_of_bill, ca.`make`, ca.`condition`, cat.`name` AS cat_name 
    FROM addresses AS ad
    JOIN courses AS co ON ad.id = co.from_address_id
    JOIN clients AS cl ON co.client_id = cl.id
    JOIN cars AS ca ON co.car_id = ca.id
    JOIN categories AS cat ON ca.category_id = cat.id
    WHERE ad.`name` = address_name
    ORDER BY ca.make, cl.full_name;
END $$

DELIMITER ;


SELECT EXTRACT(DAY FROM '2000-02-03'), DAY('2000-02-03');