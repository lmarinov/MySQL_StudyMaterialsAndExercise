#Ex 01
SELECT `title`
FROM `books`
WHERE SUBSTRING(title, 1, 3) = "The";

#Ex 02
SELECT REPLACE(title, 'The', '***') AS `Title`
FROM books
WHERE SUBSTRING(title, 1, 3) = "The";

#Ex 03
SELECT ROUND(SUM(`cost`), 2) AS `Total book price`
FROM books;

#Ex 04
SELECT concat_ws(' ', first_name, last_name), timestampdiff(day, `born`, `died`) AS `Days lived`
FROM `authors`;

#Ex 05
SELECT title
FROM books
WHERE title LIKE '%Harry Potter%'
# Alternative: WHERE LOCATE('Harry Potter', title,  1) > 0
ORDER BY id;