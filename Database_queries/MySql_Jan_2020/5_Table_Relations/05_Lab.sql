#Ex 01

CREATE TABLE mountains(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL
);

CREATE TABLE peaks (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL,
	mountain_id INT,
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY (mountain_id)
    REFERENCES mountains(id)
);

#Ex 02
SELECT `driver_id`, `vehicle_type`, concat(first_name, ' ',  last_name) AS driver_name
FROM vehicles AS v
JOIN campers AS c
ON v.driver_id = c.id;

#Ex 03
SELECT `starting_point` AS `route_starting_point`, 
`end_point` AS `route_ending_point`, 
`leader_id`, concat(first_name, ' ', last_name) AS `leader_name`
FROM `routes` AS r
JOIN `campers` AS c
ON r.leader_id = c.id;


#Ex 04
ALTER TABLE `mountains` 
ADD CONSTRAINT fk_mountains_peaks
FOREIGN KEY (id)
REFERENCES peaks(mountain_id) 
ON DELETE CASCADE;
-- cascade delete should be added to the craete queries
-- for judge submissions. Otherwise, it works correctly.

#Ex 05
CREATE TABLE `clients`(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    `client_name` VARCHAR(100)
);

CREATE TABLE `projects`(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    client_id INT(11),
    project_lead_id INT(11),
    CONSTRAINT `fk_projects_clients`
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE `employees`(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    project_id INT(11),
    CONSTRAINT `fk_employees_projects`
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

ALTER TABLE `projects`
ADD CONSTRAINT `fk_projects_employees`
FOREIGN KEY (project_lead_id) REFERENCES `employees`(id);
