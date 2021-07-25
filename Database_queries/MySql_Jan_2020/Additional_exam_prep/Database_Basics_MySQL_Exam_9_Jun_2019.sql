# Ex 01

CREATE DATABASE ruk_database;
use ruk_database;

CREATE TABLE branches(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE employees(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    started_on DATE NOT NULL,
    branch_id INT(11) NOT NULL,
    CONSTRAINT fk_employees_branches
    FOREIGN KEY (branch_id)
    REFERENCES branches(id)
);

CREATE TABLE clients(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    age INT(11) NOT NULL
);

CREATE TABLE employees_clients(
	employee_id INT(11),
    client_id INT(11),
    CONSTRAINT fk_employees_clients_employees
    FOREIGN KEY (employee_id)
    REFERENCES employees(id),
    CONSTRAINT fk_employees_clients_clients
    FOREIGN KEY (client_id)
    REFERENCES clients(id)
);

CREATE TABLE bank_accounts(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(10) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    client_id INT(11) NOT NULL UNIQUE,
    CONSTRAINT fk_bank_accounts_clients
    FOREIGN KEY (client_id)
    REFERENCES clients(id)
);

CREATE TABLE cards(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    card_number VARCHAR(19) NOT NULL UNIQUE,
    card_status VARCHAR(7) NOT NULL,
    bank_account_id INT(11) NOT NULL,
    CONSTRAINT fk_cards_bank_accounts
    FOREIGN KEY (bank_account_id)
    REFERENCES bank_accounts(id)
);

# Ex 02

INSERT INTO cards(card_number, card_status, bank_account_id)
SELECT REVERSE(full_name), 'Active', id FROM clients AS c
WHERE c.id BETWEEN 191 AND 200;

# Ex 03

UPDATE employees_clients
SET employee_id = (SELECT * FROM (SELECT employee_id
	FROM employees_clients
	GROUP BY employee_id
	ORDER BY COUNT(client_id), employee_id
	LIMIT 1) AS employee_with_fewest_clients)
WHERE employee_id = client_id;

UPDATE employees_clients AS e
JOIN (SELECT employee_id
	FROM employees_clients
	GROUP BY employee_id
	ORDER BY COUNT(client_id), employee_id
	LIMIT 1) AS ewfc ON true
SET e.employee_id = ewfc.employee_id
WHERE e.employee_id = e.client_id;

# Ex 04

DELETE e FROM employees AS e
LEFT JOIN employees_clients AS ec ON e.id = ec.employee_id
WHERE ec.client_id IS NULL;


# Ex 05

SELECT id, full_name FROM clients
ORDER BY id;

# Ex 06

SELECT e.id, concat_ws(' ', e.first_name, e.last_name) AS full_name, concat('$', e.salary) AS salary, e.started_on
FROM employees AS e
WHERE e.salary >= 100000
AND e.started_on >= '2018-01-01'
ORDER BY e.salary DESC, e.id;

# Ex 07

SELECT c.id, concat(c.card_number, ' : ', cl.full_name) AS card_token
FROM cards AS c
JOIN bank_accounts AS ba ON c.bank_account_id = ba.id
JOIN clients AS cl ON cl.id = ba.client_id
ORDER BY c.id DESC;

# Ex 08

SELECT concat_ws(' ', e.first_name, e.last_name) as `name`, e.started_on, COUNT(ec.client_id) AS count_of_clients
FROM employees_clients AS ec
JOIN employees AS e ON ec.employee_id = e.id
GROUP BY ec.employee_id
ORDER BY count_of_clients DESC, e.id
LIMIT 5;

# Ex 09

SELECT b.`name`, COUNT(c.id) AS count_of_cards
FROM branches AS b
LEFT JOIN employees AS e ON b.id = e.branch_id
LEFT JOIN employees_clients AS ec ON e.id = ec.employee_id
LEFT JOIN clients AS cl ON ec.client_id = cl.id
LEFT JOIN bank_accounts AS ba ON cl.id = ba.client_id
LEFT JOIN cards AS c ON ba.id = c.bank_account_id
GROUP BY b.id
ORDER BY count_of_cards DESC, b.`name`;

# Ex 10

DELIMITER $$

CREATE FUNCTION udf_client_cards_count(`name` VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE cards INT;
    
    SET cards := (SELECT t1.count FROM (SELECT cl.full_name, COUNT(cl.id) AS `count`
    FROM clients AS cl
	JOIN bank_accounts AS ba ON cl.id = ba.client_id
    JOIN cards AS c ON c.bank_account_id = ba.id
    GROUP BY cl.id
    HAVING cl.full_name = `name`) AS t1);
    
    RETURN cards;
END $$

DELIMITER ;

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';


# Ex 11

DELIMITER $$

CREATE PROCEDURE udp_clientinfo(full_name VARCHAR(50))
BEGIN
	SELECT cl.full_name, cl.age, ba.account_number, concat('$', ba.balance) AS balance
    FROM clients AS cl
    JOIN bank_accounts AS ba ON cl.id = ba.client_id
    WHERE cl.full_name = full_name;
END $$

DELIMITER ;
