#EX 01
SELECT * 
FROM soft_uni
ORDER BY `department_id`;

#EX 02
SELECT `name` 
FROM `departments`
ORDER BY `department_id`;

#EX 03
SELECT first_name, last_name, salary 
FROM `employees`;

#Ex 04
SELECT first_name, middle_name, last_name
FROM `employees`;

#Ex 05
SELECT concat(`first_name`, '.', `last_name`, '@', 'softuni.bg')
AS `full_email_address`
FROM `employees`;

#Ex 06
SELECT DISTINCT `salary` FROM `employees`
ORDER BY `employee_id`;

#Ex 07
SELECT * FROM `employees`
WHERE job_title = 'Sales Representative';

#Ex 08
SELECT first_name, last_name, job_title FROM `employees`
WHERE `salary` BETWEEN 20000 AND 30000
ORDER BY `employee_id`;

#Ex 09
SELECT concat_ws(' ', first_name, middle_name, last_name)
AS "Full Name"
FROM `employees`
WHERE `salary` IN (25000, 14000, 12500, 23600);

#Ex 10
SELECT first_name, last_name FROM `employees`
WHERE `manager_id` IS NULL;

#Ex 11
SELECT first_name, last_name, salary 
FROM `employees`
WHERE `salary` > 50000
ORDER BY `salary` DESC;

#Ex 12
SELECT first_name, last_name 
FROM `employees`
ORDER BY `salary` DESC
LIMIT 5;

#Ex 13
SELECT first_name, last_name 
FROM `employees`
WHERE NOT `department_id` = 4;	

#Ex 14
SELECT * 
FROM `employees`
ORDER BY `salary` DESC, 
`first_name` ASC, 
`last_name` DESC, 
`middle_name` ASC;

#Ex 15
CREATE VIEW `v_employees_salaries`
AS SELECT first_name, last_name, salary
FROM `employees`;

#Ex 16
CREATE VIEW `v_employees_job_titles`
AS SELECT concat_ws(' ', first_name, middle_name, last_name) AS `full_name`, job_title
FROM `employees`;

#Ex 17
SELECT DISTINCT job_title
FROM `employees`
ORDER BY job_title ASC;

#Ex 18
SELECT * 
FROM `projects`
ORDER BY `start_date` ASC, `name`, `project_id`
LIMIT 10;

#Ex 19
SELECT first_name, last_name, hire_date
FROM `employees`
ORDER BY `hire_date` DESC
LIMIT 7;

#Ex 20
UPDATE `employees`
SET `salary` = `salary` * 1.12
WHERE `department_id` IN (1, 2, 4,  11);
SELECT salary FROM `employees`;

#Ex 21
SELECT `peak_name` FROM `peaks`
ORDER BY `peak_name` ASC;

#Ex 22
SELECT country_name, population
FROM `countries`
WHERE `continent_code` = 'EU'
ORDER BY `population` DESC, `country_name` ASC
LIMIT 30;

#Ex 23
SELECT country_name, country_code,
CASE 
WHEN currency_code = 'EUR' THEN "Euro" 
ELSE "Not Euro"
END AS `currency`
FROM `countries`
ORDER BY `country_name` ASC;

#Ex 24
SELECT `name`
FROM `characters`
ORDER BY `name` ASC;