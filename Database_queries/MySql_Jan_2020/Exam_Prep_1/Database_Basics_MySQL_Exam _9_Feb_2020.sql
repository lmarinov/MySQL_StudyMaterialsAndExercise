# Ex 01

CREATE DATABASE fsd;
USE fsd;
CREATE TABLE players (
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(10) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    age INT NOT NULL DEFAULT 0,
    position CHAR(1) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL DEFAULT 0,
    hire_date DATETIME,
    skills_data_id INT NOT NULL,
    team_id INT
);

CREATE TABLE players_coaches(
	player_id INT,
    coach_id INT,
    CONSTRAINT `pk_players_coaches`
    PRIMARY KEY (player_id, coach_id)
);

CREATE TABLE coaches(
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(10) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    salary DECIMAL(10,2) NOT NULL DEFAULT 0,
    coach_level INT NOT NULL DEFAULT 0
);

CREATE TABLE skills_data(
	id	INT PRIMARY KEY AUTO_INCREMENT,
	dribbling	INT DEFAULT 0 ,
	pace	INT DEFAULT 0,
	passing	INT DEFAULT 0,
	shooting	INT	DEFAULT 0,
	speed	INT	DEFAULT 0,
	strength	INT	DEFAULT 0
);

CREATE TABLE countries (
	id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE towns (
	id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    country_id INT NOT NULL,
    CONSTRAINT fk_towns_countries
    FOREIGN KEY (country_id)
    REFERENCES countries(id)
);

CREATE TABLE stadiums(
	id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    capacity INT NOT NULL,
    town_id INT NOT NULL,
    CONSTRAINT fk_stadiums_towns
    FOREIGN KEY (town_id)
    REFERENCES towns(id)
);

CREATE TABLE teams(
	id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    established DATE NOT NULL,
    fan_base BIGINT NOT NULL DEFAULT 0,
    stadium_id INT NOT NULL,
    CONSTRAINT fk_teams_stadiums
    FOREIGN KEY (stadium_id)
    REFERENCES stadiums(id)
);

ALTER TABLE `players`
ADD CONSTRAINT `fk_skills_data_id_skills_data`
FOREIGN KEY (skills_data_id)
REFERENCES skills_data(id),
ADD CONSTRAINT `fk_team_id_teams`
FOREIGN KEY (team_id)
REFERENCES teams(id);

ALTER TABLE `players_coaches`
ADD CONSTRAINT `fk_players_coaches_players`
FOREIGN KEY (player_id)
REFERENCES players(id),
ADD CONSTRAINT `fk_players_coaches_coaches`
FOREIGN KEY (coach_id)
REFERENCES coaches(id);


# Ex 02

INSERT INTO coaches (first_name, last_name, salary, coach_level)
SELECT p.first_name, p.last_name, p.salary, char_length(p.first_name)
FROM `players` AS p
WHERE p.`age` >= 45;

# Ex 03

UPDATE coaches
SET coach_level = coach_level + 1
WHERE first_name 
LIKE 'A%' 
AND id = (SELECT coach_id 
		FROM players_coaches 
        WHERE coach_id = id 
        LIMIT 1);

UPDATE coaches AS c
JOIN players_coaches AS pk
ON pk.coach_id = c.id
SET coach_level = coach_level + 1
WHERE first_name LIKE 'A%';

# Ex 04

DELETE FROM players AS p
WHERE age >= 45;

# Ex 05

SELECT first_name, age, salary
FROM players
ORDER BY salary DESC;

# Ex 06

SELECT p.id, concat_ws(' ', p.first_name, p.last_name) AS full_name, p.age, p.position, p.hire_date
FROM players AS p
JOIN `skills_data` AS s
ON p.skills_data_id = s.id
WHERE p.age < 23 AND p.position = 'A' AND s.strength > 50 AND hire_date IS NULL
ORDER BY p.salary, p.age;

# Ex 07

SELECT t.`name`, t.established, t.fan_base, (SELECT COUNT(p.id) FROM players AS p WHERE p.team_id = t.id) AS players_count
FROM teams AS t
ORDER BY players_count DESC, fan_base DESC; 

# Ex 08

SELECT MAX(sd.speed) AS max_speed, towns.`name`
FROM players AS p
JOIN skills_data AS sd 
ON p.skills_data_id = sd.id
RIGHT JOIN teams AS t 
ON p.team_id = t.id
RIGHT JOIN stadiums AS s
ON t.stadium_id = s.id
RIGHT JOIN towns
ON s.town_id = towns.id
WHERE t.`name` != 'Devify'
GROUP BY towns.`name`
ORDER BY max_speed DESC, towns.`name`;

# EX 09

SELECT c.`name`, COUNT(p.id) AS total_count_of_players, SUM(salary) AS total_sum_of_salaries
FROM players AS p
RIGHT JOIN teams AS t ON p.team_id = t.id
JOIN stadiums AS s ON t.stadium_id = s.id
JOIN towns ON s.town_id = towns.id
RIGHT JOIN countries AS c ON towns.country_id = c.id
GROUP BY c.`name`
ORDER BY total_count_of_players DESC, c.`name`;


# Ex 10

DELIMITER $$

CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(*) 
    FROM stadiums AS s
    JOIN teams AS t ON t.stadium_id = s.id
    JOIN players AS p ON p.team_id = t.id
    WHERE s.`name` = stadium_name
    );
END $$

DELIMITER ;

# Ex 11

DELIMITER $$

CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
	SELECT concat(p.first_name, ' ', p.last_name) AS full_name, p.age, p.salary, sd.dribbling, sd.speed, t.`name` AS team_name
    FROM `players` AS p
    JOIN `skills_data` AS sd
    ON p.skills_data_id = sd.id
    JOIN `teams` AS t
    ON p.team_id = t.id
    WHERE sd.dribbling >= min_dribble_points
    AND sd.speed > (SELECT avg(speed) FROM skills_data)
    AND t.`name` = team_name
    ORDER BY sd.speed DESC
    LIMIT 1;
END $$

DELIMITER ;

SELECT * FROM `skills_data`;