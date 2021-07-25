#Ex 01

DELIMITER $$$

CREATE FUNCTION `ufn_count_employees_by_town`(town_name VARCHAR(20))
RETURNS INTEGER
DETERMINISTIC
BEGIN
DECLARE e_count INT;
	SET e_count := (SELECT COUNT(*)
		FROM employees AS e
		JOIN addresses AS a
		USING (address_id)
		JOIN towns AS t
		USING (town_id)
		WHERE t.`name` = town_name
		GROUP BY t.`name`);
RETURN e_count;
END$$$

DELIMITER ;


SELECT `ufn_count_employees_by_town`("Sofia") AS `count`;

#Ex 02

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_raise_salaries`(`dep_name` VARCHAR(50))
BEGIN
UPDATE employees 
INNER JOIN departments AS d
USING (department_id)
SET salary = salary * 1.05
WHERE d.`name` = `dep_name`;

END$$

DELIMITER ;
;

CALL `usp_raise_salaries`('Sales');

#Ex 03
DELIMITER $$
CREATE PROCEDURE `usp_raise_salary_by_id` (emp_id INT)
BEGIN
DECLARE total_Rows INT;
SET total_Rows = 0;
	START TRANSACTION;
	IF ((SELECT COUNT(*) FROM employees WHERE employee_id = emp_id) = 1)
	THEN
		UPDATE employees AS e
		SET salary = salary * 1.05
		WHERE e.employee_id = emp_id;
        SET total_Rows = total_Rows + row_count();
        -- found_rows\(\) is used to find rows affected row_count() is used for DML, while the former is used in DDL
        SELECT total_Rows As TotalRowsAffected;
        COMMIT;
	ELSE 
		ROLLBACK;
	END IF;
END$$

DELIMITER ;

CALL `usp_raise_salary_by_id`(1);


#Ex 04

CREATE TABLE `deleted_employees`(
employee_id INT PRIMARY KEY AUTO_INCREMENT, 
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
middle_name VARCHAR(50) DEFAULT NULL,
job_title VARCHAR(50) NOT NULL,
department_id INT NOT NULL,
salary DECIMAL(19, 4) NOT NULL
);

DELIMITER $$

CREATE TRIGGER `employees_AFTER_DELETE` AFTER DELETE ON `employees` FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees(first_name,last_name,middle_name,job_title,department_id,salary)
    VALUES(OLD.first_name, OLD.last_name,OLD.middle_name, OLD.job_title, OLD.department_id, OLD.salary);
    
END$$

DELIMITER ;
;