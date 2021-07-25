#Ex 01

SELECT e.`employee_id`, e.`job_title`, a.`address_id`, a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
USING (`address_id`)
ORDER BY `address_id`
LIMIT 5;

#Ex 02

SELECT e.`first_name`, e.`last_name`, t.`name`, a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
USING (`address_id`)
JOIN `towns` AS t
USING (`town_id`)
ORDER BY e.`first_name`, e.`last_name`
LIMIT 5;

#Ex 03

SELECT 
    e.`employee_id`, e.`first_name`, e.`last_name`, d.`name`
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    d.`name` = 'Sales'
ORDER BY `employee_id` DESC;

#Ex 04

SELECT e.`employee_id`, e.`first_name`, e.`salary`, d.`name`
FROM `departments` AS d
JOIN `employees` AS e
USING (`department_id`)
WHERE e.`salary` > 15000
ORDER BY `department_id` DESC
LIMIT 5;

#Ex 05
SELECT e.`employee_id`, e.`first_name`
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep
USING (`employee_id`)
WHERE ep.`project_id` IS NULL
ORDER BY `employee_id` DESC
LIMIT 3;

SELECT e.`employee_id`, e.`first_name`
FROM `employees` AS e
WHERE e.`employee_id` NOT IN 
(SELECT e2.`employee_id` FROM `employees_projects` AS e2)
ORDER BY e.`employee_id` DESC
LIMIT 3;

#Ex 06

SELECT e.`first_name`, e.`last_name`, e.`hire_date`, d.`name`
FROM `employees` AS e
JOIN `departments` AS d
USING (`department_id`) 
WHERE YEAR( e.`hire_date`) >= 1999 AND d.`name` IN ("Sales", "Finance")
ORDER BY `hire_date`;

# Ex 07

SELECT 
    e.`employee_id`, e.`first_name`, p.`name`
FROM
    `employees` AS e
        JOIN
    `employees_projects` ep ON e.`employee_id` = ep.`employee_id`
        JOIN
    `projects` AS p USING (`project_id`)
WHERE
    DATE(p.`start_date`) > '2002-08-13'
        AND p.`end_date` IS NULL
ORDER BY e.`first_name` , p.`name`
LIMIT 5;

# Ex 08
SELECT 
    e.`employee_id`, e.`first_name`, IF(YEAR(p.`start_date`) >=2005, NULL, p.`name`) AS `project_name`
FROM
    `employees` AS e
        JOIN
    `employees_projects` ep ON e.`employee_id` = ep.`employee_id`
        JOIN
    `projects` AS p USING (`project_id`)
WHERE
	e.`employee_id` = 24
ORDER BY `project_name`;

#Ex 09
SELECT e.`employee_id`, e.`first_name`, e.`manager_id`, m.`first_name` AS `manager_name`
FROM `employees` AS e
JOIN `employees` AS m
ON e.`manager_id` = m.`employee_id`
WHERE e.`manager_id` IN (3, 7)
ORDER BY e.`first_name`;


# Ex 10

SELECT e.`employee_id`, concat_ws(' ', e.`first_name`, e.`last_name`) AS `employee_name`, concat_ws(' ', m.`first_name`, m.`last_name`) AS `manager_name`, d.`name` AS `department_name` 
FROM `employees` AS e
JOIN `employees` AS m
ON e.`manager_id` = m.`employee_id`
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
ORDER BY e.`employee_id`
LIMIT 5;

# Ex 11
SELECT AVG(`salary`) AS `min_average_salary`
FROM `employees`
GROUP BY `department_id`
ORDER BY `min_average_salary`
LIMIT 1;

#Ex 12
SELECT mc.`country_code`, m.`mountain_range`, p.`peak_name`, p.`elevation`
FROM `mountains_countries` AS mc
JOIN `mountains` AS m
ON m.`id` = mc.`mountain_id`
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`
WHERE mc.`country_code` = "BG" AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

#Ex 13
SELECT mc.`country_code`, COUNT(m.`mountain_range`) AS mountain_range
FROM `mountains_countries` AS mc
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
WHERE mc.`country_code` IN ('BG', 'US', 'RU')
GROUP BY mc.`country_code`
ORDER BY `mountain_range` DESC;

#Ex 14
SELECT c.`country_name`, r.`river_name`
FROM `countries` AS c
LEFT JOIN `countries_rivers` AS cr
ON c.`country_code` = cr.`country_code`
LEFT JOIN `rivers` AS r
ON r.`id` = cr.`river_id`
WHERE c.`continent_code` = "AF"
ORDER BY c.`country_name`
LIMIT 5;

#Ex 15
SELECT co.`continent_code`, co.`currency_code`, 
COUNT(country_name) AS `currency_usage`
FROM `countries` AS co
GROUP BY co.`continent_code`, co.`currency_code`
HAVING `currency_usage` = (
	SELECT COUNT(`country_code`) AS c
	FROM `countries` AS cc 
	WHERE cc.`continent_code` = co.`continent_code` 
	GROUP BY cc.`currency_code`
	ORDER BY c DESC
	LIMIT 1)
AND `currency_usage` > 1
ORDER BY `continent_code`, `currency_code`;

SELECT * FROM `countries`;


SELECT currency_code 
FROM `countries` AS cc 
WHERE cc.`continent_code` = co.`continent_code` 
AND cc.`currency_code` = co.`currency_code`;

#Ex 16
SELECT COUNT(*) AS country_count FROM countries
WHERE country_code NOT IN (
SELECT country_code FROM mountains_countries);

#Ex 17
SELECT c.`country_name`,MAX(p.`elevation`) AS `highest_peak_elevation`,  MAX(r.`length`) AS `longest_river_length`
FROM `countries` AS c
JOIN `countries_rivers` AS cr
ON c.`country_code` = cr.`country_code`
JOIN `rivers` AS r
ON r.`id` = cr.`river_id`
JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`
GROUP BY c.`country_code`
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC, c.`country_name`
LIMIT 5;