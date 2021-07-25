#Ex 01
CREATE TABLE employees(
		id INT PRIMARY KEY AUTO_INCREMENT,
        first_name VARCHAR(50) NOT NULL,
        last_name VARCHAR(50) NOT NULL
);

CREATE TABLE categories(
		id INT PRIMARY KEY AUTO_INCREMENT,
        `name` VARCHAR(50) NOT NULL
);

CREATE TABLE products(
		id INT PRIMARY KEY AUTO_INCREMENT,
        `name` VARCHAR(50) NOT NULL,
        category_id INT NOT NULL
);

#Ex 02
INSERT INTO employees (first_name, last_name)
VALUES(
	'Pencho', 'Penchev'
);

INSERT INTO employees (first_name, last_name)
VALUES(
	'Toncho', 'Trenchev'
);

INSERT INTO employees (first_name, last_name)
VALUES(
	'Gincho', 'Penchev'
);

#Ex 03
ALTER TABLE employees
ADD middle_name VARCHAR(50);

#Ex 04
ALTER TABLE products
ADD CONSTRAINT my_fk
FOREIGN KEY (category_id) REFERENCES categories(id)

#Ex 05
ALTER TABLE employees
MODIFY COLUMN middle_name VARCHAR(100);