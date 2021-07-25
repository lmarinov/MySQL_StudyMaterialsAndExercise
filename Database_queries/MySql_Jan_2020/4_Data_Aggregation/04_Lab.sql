#Ex 01
SELECT `department_id`, count(*) AS 'Number of employees'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`, `Number of employees`;

#Ex 02
SELECT department_id, round(avg(salary), 2) AS `Average Salary`
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;

#Ex 03
SELECT e.`department_id`, ROUND(MIN(`salary`), 2) AS `Min Salary`
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING `Min Salary` > 800;

#Ex 04
SELECT COUNT(*) AS `appetizers`
FROM `products` AS p
WHERE p.`category_id` = 2 AND p.`price` > 8;

#Ex 05

SELECT p.`category_id`, 
ROUND(AVG(p.`price`), 2) AS `Average Price`, 
ROUND(MIN(p.`price`), 2) AS `Cheapest Product`, 
ROUND(MAX(p.`price`), 2) AS `Most Expensive Product`
FROM `products` AS p
GROUP BY p.`category_id`;