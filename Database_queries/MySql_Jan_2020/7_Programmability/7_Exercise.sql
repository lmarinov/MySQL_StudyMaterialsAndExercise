#Ex 01

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT e.first_name, e.last_name 
    FROM employees AS e
    WHERE e.salary > 35000
    ORDER BY e.first_name, e.employee_id;
END$$

DELIMITER ;

# Ex 02
DELIMITER $$

CREATE PROCEDURE usp_get_employees_salary_above(salary_level DECIMAL(19, 4))
BEGIN
	SELECT e.first_name, e.last_name 
    FROM `employees` AS e
    WHERE e.salary >= salary_level
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$

DELIMITER ;

#Ex 03

DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(town_prefix VARCHAR(20))
BEGIN 
	SELECT `name` AS town_name
    FROM towns AS t
    WHERE t.`name` LIKE CONCAT(town_prefix, '%')
    ORDER BY t.`name`;
END$$
DELIMITER ;

#Ex 04

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT e.first_name, e.last_name 
    FROM towns AS t
    JOIN addresses AS a
    ON t.`town_id` = a.`town_id`
    JOIN `employees` AS e
    ON e.`address_id` = a.`address_id`
    WHERE t.`name` = town_name
    ORDER BY e.`first_name`, e.`last_name`, e.`employee_id`;
END$$

DELIMITER ;

#Ex 05

DELIMITER $$
CREATE FUNCTION `ufn_get_salary_level` (employee_salary VARCHAR(10))
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(10);
    
    IF(employee_salary < 30000)
		THEN SET salary_level := 'Low';
	ELSEIF (employee_salary <= 50000)
		THEN SET salary_level := 'Average';
	ELSE 
		SET salary_level := 'High';
	END IF;
RETURN salary_level;
END$$
DELIMITER ;

#Ex 06

DELIMITER $$

CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level DECIMAL(19,4))
BEGIN 
	SELECT e.first_name, e.last_name
    FROM employees AS e
    WHERE (SELECT ufn_get_salary_level(e.salary)) = salary_level
    ORDER BY e.first_name DESC, e.last_name DESC;
END $$

DELIMITER ;

# Ex 07

DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BIT
DETERMINISTIC
BEGIN
RETURN (SELECT word REGEXP(CONCAT('^[', set_of_letters, ']+$')));
END$$
DELIMITER ;

# Ex 08

DELIMITER $$

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT concat_ws(' ', ah.first_name, ah.last_name) AS full_name
    FROM account_holders AS ah
    ORDER BY full_name;
END $$

DELIMITER ;

CALL usp_get_holders_full_name();

# Ex 09

DELIMITER $$
CREATE PROCEDURE `usp_get_holders_with_balance_higher_than` (balance_higher_than DECIMAL(19, 4))
BEGIN
	SELECT ah.first_name, ah.last_name
    FROM account_holders AS ah
    JOIN (SELECT ac.`account_holder_id` FROM accounts AS ac
		GROUP BY ac.account_holder_id
		HAVING SUM(ac.balance) > balance_higher_than) AS a
    ON a.`account_holder_id` = ah.`id`
    ORDER BY a.account_holder_id;
END $$
DELIMITER ;


# Ex 10

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(balance DECIMAL(19, 4), interest DECIMAL(19, 4), years INT)
RETURNS DECIMAL(19, 4)
DETERMINISTIC
BEGIN

RETURN balance * pow(1 + interest, years);
END $$

DELIMITER ;

# Ex 11

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(
acc_id INT, interest DECIMAL(19, 4))
BEGIN
DECLARE default_interest_years INT;
SET default_interest_years := 5;
	SELECT a.id AS account_id, ah.first_name, ah.last_name, a.balance AS current_balance,
    (SELECT ufn_calculate_future_value(a.balance, interest, default_interest_years)) AS balance_in_5_years
    FROM account_holders AS ah
    JOIN accounts a
    ON ah.id = a.account_holder_id
    WHERE a.id = acc_id;
END$$

DELIMITER ;

# Ex 12

DELIMITER $$

CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	IF money_amount > 0
    AND
    (SELECT a.id FROM `accounts` AS a WHERE a.id = account_id) IS NOT NULL
    THEN
    START TRANSACTION;
		UPDATE `accounts` AS a
        SET a.balance = a.balance + money_amount
        WHERE a.id = account_id;
    ELSE
    ROLLBACK;
    END IF;
END $$

DELIMITER ;

SELECT id, balance FROM accounts WHERE id = 1;

call usp_deposit_money(1, 10);


# Ex 13

DELIMITER $$

CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	IF money_amount > 0
    AND
    (SELECT a.id FROM `accounts` AS a WHERE a.id = account_id) IS NOT NULL
    THEN
    START TRANSACTION;
		UPDATE `accounts` AS a
        SET a.balance = a.balance - money_amount
        WHERE a.id = account_id;
    
    IF(SELECT a.balance FROM `accounts` AS a WHERE a.id = account_id) >= money_amount
    THEN
    COMMIT;
    ELSE
    ROLLBACK;
    END IF;
    END IF;
END $$

DELIMITER ;

SELECT id, balance FROM accounts WHERE id = 1;

call usp_withdraw_money(1, 10);


# Ex 14
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	IF amount > 0 
    AND
    (SELECT a.id FROM `accounts` AS a WHERE id = from_account_id) IS NOT NULL
    AND
    (SELECT a.id FROM `accounts` AS a WHERE id = to_account_id) IS NOT NULL
    THEN
    START TRANSACTION;
    
    UPDATE accounts a 
    SET balance = balance - amount
    WHERE a.id = from_account_id;
    
    UPDATE accounts a 
    SET balance = balance + amount
    WHERE a.id = to_account_id;
    
    IF(SELECT balance FROM accounts a WHERE a.id = from_account_id) < 0
    THEN ROLLBACK;
    ELSE COMMIT;
    END IF;
    
    END IF;
END$$
DELIMITER ;


# Ex 15

CREATE TABLE `logs`(
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT, 
old_sum DECIMAL(19, 4), 
new_sum DECIMAL(19, 4)
);

DELIMITER $$
CREATE TRIGGER tr_balance_change
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
	INSERT INTO `logs`(account_id, old_sum, new_sum)
    VALUES(OLD.id, OLD.balance, NEW.balance);
END $$
DELIMITER ;


# Ex 16

CREATE TABLE `notification_emails`(
	id INT PRIMARY KEY AUTO_INCREMENT,
    recipient INT,
    `subject` VARCHAR(100),
    body VARCHAR(100)
);

DELIMITER $$
CREATE TRIGGER tr_log_inserted
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails(`recipient`, `subject`, `body`)
    VALUES(NEW.account_id, concat_ws(' ', 'Balance change for account:', NEW.account_id), concat('On ', DATE_FORMAT(CURDATE(), '%M %d %Y at %r'), ' your balance was changed from ', ROUND(new.`old_sum`), ' to ', ROUND(new.`new_sum`), '.'));
END $$

DELIMITER ;

call usp_withdraw_money(1, 10);

SELECT * FROM `notification_emails`;

SELECT * FROM logs;

UNLOCK TABLES;

DROP TRIGGER tr_log_inserted;

