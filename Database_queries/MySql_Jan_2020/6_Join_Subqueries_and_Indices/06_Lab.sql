SELECT * 
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id;

SELECT * 
FROM employees
LEFT JOIN departments 
USING (`department_id`);

#Ex 01

SELECT e.employee_id, concat(e.first_name, ' ', e.last_name) AS full_name, d.department_id, d.`name` AS department_name
FROM departments AS d
JOIN employees AS e
on d.manager_id = e.employee_id
order by e.employee_id
LIMIT 5;


#Ex 02

SELECT t.town_id, t.`name` AS town_name, a.address_text
FROM towns AS t
JOIN addresses AS a
USING (town_id)
WHERE t.`name` IN ("San Francisco", "Sofia", "Carnation")
ORDER BY town_id, address_id;

#Ex 03
SELECT employee_id, first_name, last_name, department_id, salary
FROM employees
WHERE `manager_id` IS NULL;

#Ex 04

SELECT COUNT(*) AS count
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
