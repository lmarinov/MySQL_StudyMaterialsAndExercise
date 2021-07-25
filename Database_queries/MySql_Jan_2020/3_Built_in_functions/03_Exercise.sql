#Ex 01
SELECT first_name, last_name 
FROM `employees`
WHERE substring(`first_name`, 1, 2) = "Sa";

#Ex 02
SELECT first_name, last_name
FROM `employees`
WHERE locate("ei", last_name)
ORDER BY employee_id;

#Ex 03
SELECT first_name 
FROM `employees`
WHERE `department_id` IN (3, 10)
AND YEAR(`hire_date`) BETWEEN ("1995") AND ("2005")
ORDER BY employee_id;

#Ex 04
SELECT first_name, last_name
FROM `employees`
WHERE `job_title` NOT LIKE '%engineer%'
ORDER BY employee_id;

#Ex 05
SELECT `name` 
FROM `towns`
WHERE char_length(`name`) IN (5, 6)
ORDER BY `name`;

#Ex 06
SELECT `town_id`, `name` 
FROM `towns`
WHERE LEFT(`name` , 1) IN ('m', 'k', 'b', 'e')
ORDER BY `name`;

#Ex 07
SELECT `town_id`, `name` 
FROM `towns`
WHERE LEFT(`name` , 1) NOT IN ('r', 'd', 'b')
ORDER BY `name`;

#Ex 08
CREATE VIEW `v_employees_hired_after_2000`
AS SELECT first_name, last_name
FROM `employees`
WHERE YEAR(`hire_date`) > 2000;

SELECT * FROM `v_employees_hired_after_2000`;

#Ex 09
SELECT first_name, last_name
FROM `employees`
WHERE char_length(`last_name`) = 5;

#Ex 10
USE `geography`;

SELECT country_name, iso_code 
FROM `countries`
WHERE `country_name` LIKE '%a%a%a%'
ORDER BY `iso_code`;

#Ex 11
SELECT peak_name, river_name, concat(LOWER(LEFT(`peak_name`, char_length(`peak_name`) - 1)), LOWER(`river_name`)) AS `mix`
FROM `peaks`, `rivers`
WHERE RIGHT(`peak_name`, 1) = LEFT(`river_name` , 1)
ORDER BY `mix`;

#Ex 12
SELECT `name`, date_format(`start`, '%Y-%m-%d') AS `start`
FROM `games`
WHERE YEAR(`start`) IN ("2011", "2012")
ORDER BY `start`, `name`
LIMIT 50;

#Ex 13
SELECT `user_name`,  SUBSTRING(`email`, LOCATE('@', `email`) + 1)  AS `email_provider`
FROM `users`
ORDER BY `email_provider`, `user_name`;

#Ex 14
SELECT user_name, ip_address
FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

#Ex 15
SELECT `name`, 
CASE 
	WHEN hour(`start`) < 12 THEN "Morning"
    WHEN hour(`start`) < 18 THEN "Afternoon"
    WHEN hour(`start`) < 24 THEN "Evening"
END AS "Part of the Day", 
CASE
	WHEN `duration` < 4 THEN "Extra Short"
	WHEN `duration` < 7 THEN "Short"
	WHEN `duration` < 11 THEN "Long"
	ELSE "Extra Long"
END AS "Duration"
FROM `games`;

#Ex 16
SELECT 
    product_name,
    order_date,
    DATE_ADD(`order_date`, INTERVAL 3 DAY) AS pay_due,
    DATE_ADD(`order_date`, INTERVAL 1 MONTH) AS deliver_due
FROM
    `orders`;