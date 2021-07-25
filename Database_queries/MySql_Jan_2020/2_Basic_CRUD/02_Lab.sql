SELECT BINARY "Kasd" 
REGEXP BINARY '^[K]{1}[a-z]*$'
AS result;

#Ex 01
SELECT id, first_name, last_name, job_title 
FROM `employees`;

#Ex 02
SELECT id, concat_ws(' ', first_name, last_name) AS full_name, job_title, salary
FROM `employees`
WHERE `salary` > 1000.00;

#Ex 03
UPDATE `employees`
SET `salary` = `salary` + 100
WHERE `job_title` = "Manager";

SELECT `salary`
AS `salaries`
FROM `employees`;

#Ex 04
CREATE VIEW `v_highest_paid_employee`
AS SELECT *
FROM `employees`
ORDER BY `salary` DESC
LIMIT 1;

SELECT * FROM `v_highest_paid_employee`;

#EX 05
SELECT * 
FROM `employees`
WHERE `department_id` = 4 AND `salary` >= 1000
ORDER BY `id`;

#Ex 06
DELETE FROM `employees`
WHERE `department_id` IN (1,2);

SELECT * FROM `employees`;