# Ex 01

CREATE DATABASE softuni_stores_system;

use softuni_stores_system;

CREATE TABLE towns(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE addresses(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    town_id INT NOT NULL,
    CONSTRAINT fk_addresses_towns
    FOREIGN KEY (town_id)
    REFERENCES towns(id)
);

CREATE TABLE stores(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    rating FLOAT NOT NULL,
    has_parking TINYINT(1) DEFAULT 0,
    address_id INT NOT NULL,
    CONSTRAINT fk_stores_addresses
    FOREIGN KEY (address_id)
    REFERENCES addresses(id)
);

CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(15) NOT NULL UNIQUE,
    middle_name CHAR(1),
    last_name VARCHAR(20) NOT NULL,
    salary DECIMAL(19, 2) NOT NULL DEFAULT 0,
    hire_date DATE NOT NULL,
    manager_id INT,
    store_id INT NOT NULL,
    CONSTRAINT fk_employees_stores
    FOREIGN KEY (store_id)
    REFERENCES stores(id),
    CONSTRAINT sr_manager_employee
    FOREIGN KEY (manager_id)
    REFERENCES employees(id)
);

CREATE TABLE pictures(
	id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(100) NOT NULL,
    added_on DATETIME NOT NULL
);


CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);


CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL UNIQUE,
    best_before DATE,
    price DECIMAL(10, 2) NOT NULL,
    `description` TEXT,
    category_id INT NOT NULL,
    picture_id INT NOT NULL,
    CONSTRAINT fk_products_categories
    FOREIGN KEY (category_id)
    REFERENCES categories(id),
    CONSTRAINT fk_products_pictures
    FOREIGN KEY (picture_id)
    REFERENCES pictures(id)
);

CREATE TABLE products_stores(
	product_id INT NOT NULL,
    store_id INT NOT NULL,
    CONSTRAINT pk_products_stores
    PRIMARY KEY (product_id, store_id),
    CONSTRAINT fk_products_stores_products
    FOREIGN KEY (product_id)
    REFERENCES products(id),
    CONSTRAINT fk_products_stores_stores
    FOREIGN KEY (store_id)
    REFERENCES stores(id)
);

# Ex 02
INSERT INTO products_stores (product_id, store_id)
SELECT p.id, 1 FROM products AS p
WHERE p.id NOT IN (SELECT product_id FROM products_stores);

# Ex 03

UPDATE employees e
SET e.salary = e.salary - 500, e.manager_id = 3
WHERE YEAR(e.hire_date) > 2003 AND e.store_id NOT IN((SELECT s.id 
FROM stores AS s
WHERE s.`name` IN ('Cardguard', 'Veribet')));

# Ex 04

DELETE FROM employees
WHERE `manager_id` IS NOT NULL AND `salary` >= 6000;

# Ex 05

SELECT first_name, middle_name, last_name, salary, hire_date
FROM employees AS e
ORDER BY e.hire_date DESC;

# Ex 06

SELECT p.`name` AS product_name, price, best_before, concat(substring(`description`, 1, 10), '...') AS short_description, url FROM
products AS p
JOIN pictures AS ps
ON p.picture_id = ps.id
WHERE p.price > 20
AND YEAR(ps.added_on) < 2019
AND char_length(p.description) > 100
ORDER BY p.price DESC;

# Ex 07

SELECT s.`name`, COUNT(p.id) AS product_count, round(avg(p.price), 2) AS `avg`
FROM stores AS s
LEFT JOIN products_stores AS ps
ON s.id = ps.store_id
LEFT JOIN products AS p
ON ps.product_id = p.id
GROUP BY s.id
ORDER BY product_count DESC, `avg` DESC, s.id ASC;

# Ex 08

SELECT concat_ws(' ', e.first_name, e.last_name) AS Full_name, s.`name` AS Store_name, a.`name` AS address, e.salary
FROM employees AS e
JOIN stores AS s
ON e.store_id = s.id
JOIN addresses AS a
ON s.address_id = a.id
WHERE e.salary < 4000
AND a.`name` LIKE '%5%'
AND char_length(s.`name`) > 8
AND right(e.last_name, 1) = 'n';


# Ex 09

SELECT REVERSE(s.`name`) AS reversed_name, concat(UPPER(t.`name`), '-', a.`name`) AS full_address, count(e.id) AS `employees_count`
FROM stores AS s
JOIN addresses AS a
ON s.address_id = a.id
JOIN towns AS t
ON a.town_id = t.id
JOIN employees AS e
ON e.store_id = s.id
GROUP BY s.id
HAVING `employees_count` > 0
ORDER BY full_address;


# Ex 10

DELIMITER $$

CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(100)
DETERMINISTIC
BEGIN

RETURN (SELECT concat(e.first_name, ' ', e.middle_name, '. ', e.last_name, ' works in store for ', DATE_FORMAT(FROM_DAYS(datediff('2020-10-18', hire_date)), '%Y') + 0, ' years')
	FROM employees AS e
	JOIN stores AS s
	ON e.store_id = s.id
	WHERE s.`name` = store_name
	ORDER BY e.salary DESC
	LIMIT 1);

END $$

DELIMITER ;

SELECT udf_top_paid_employee_by_store('Stronghold');

# Ex 11

DELIMITER $$

CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN

DECLARE increase_level INT;
	 IF address_name like '0%' THEN SET increase_level = 100;
     ELSE SET increase_level = 200;
     END IF;
     
UPDATE products AS p SET price = price + increase_level
WHERE p.id IN 
	(SELECT ps.product_id FROM addresses AS a
	JOIN stores AS s ON a.id = s.address_id
	JOIN products_stores AS ps ON ps.store_id = s.id
	WHERE a.`name` = address_name
	);
END $$

DELIMITER ;


