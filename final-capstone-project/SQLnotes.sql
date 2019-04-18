create table class
(class_id serial primary key,
name varchar(100) not null);


create table student
(student_id serial primary key,
name varchar(255) not null,
email varchar(100) not null,
comments text);

create table matches
(match_id serial primary key,
size int not null,
week int not null,
count int not null);

create table instructor
(instructor_id serial primary key,
name varchar(255) not null,
password varchar(100) not null);

create table instructor_class
(instructor_id int not null,
class_id int not null,

constraint fk_instructor_class_instructor_id foreign key (instructor_id) references instructor(instructor_id),
constraint fk_instructor_class_class_id foreign key (class_id) references class(class_id));

create table instructor_student
(instructor_id int not null,
student_id int not null,

constraint fk_instructor_student_instructor_id foreign key (instructor_id) references instructor(instructor_id),
constraint fk_instructor_student_student_id foreign key (student_id) references student(student_id));

create table student_matches
(student_id int not null,
match_id int not null,

constraint fk_student_macthes_match_id foreign key (match_id) references matches(match_id),
constraint fk_student_matches_student_id foreign key (student_id) references student(student_id));

//View classes
SELECT name FROM class ORDER BY ASC;

//add a class
INSERT INTO class (class_id, name) VALUES (DEFAULT, ?)

//add instructor
INSERT INTO instructor (id, name, password) VALUES (DEFAULT, ?, ?)

//add student list ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
COPY student (student_id, name, email, comments) FROM '/Users/cho/development/final-capstone/final-capstone-team-bravo/final-capstone-project/studentList1.csv' DELIMITERS ',' CSV header FORCE QUOTE *;

//edit student
UPDATE student SET name = ?, email = ?, comments = ? WHERE name = ?

//edit matches????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
UPDATE student_matches SET match_id = ?, size = ?, week = ? WHERE match_id

//view matches
SELECT match_id, size, week, count FROM matches

//generate matches???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????


alter table student add column active boolean


create table app_user_instructor
(instructor_id int not null,
id int not null,

constraint fk_app_user_instructor_instructor_id foreign key (instructor_id) references instructor(instructor_id),
constraint fk_app_user_instructor_id foreign key (id) references app_user(id));


DELETE FROM student;

INSERT INTO instructor (instructor_id, name, password) VALUES (DEFAULT, 'Brian Lauvray', '!CodingRules1');
INSERT INTO instructor_class (instructor_id, class_id) VALUES (1, 1);

SELECT * FROM class 
JOIN instructor_class ON instructor_class.class_id = class.class_id 
JOIN instructor ON instructor.instructor_id = instructor_class.instructor_id
WHERE instructor_class.instructor_id = ? ORDER BY class.name ASC;

GRANT ALL ON instructor TO postgres;

GRANT ALL ON class TO postgres;
GRANT INSERT ON class to postgres;
GRANT ALL ON matches TO postgres;
GRANT ALL ON student TO postgres;
GRANT ALL ON app_user TO postgres;

SELECT * FROM app_user
INSERT INTO app_user (id, user_name, password, role, salt) VALUES (DEFAULT, 'blauvray', '!CodingRules1', 'instructor')
UPDATE student SET name = ?, email = ?, comments = ? WHERE student_id = ?

INSERT INTO student (student_id, name, email, comments) VALUES (DEFAULT, 'Caitlin Ho', 'cho@gmail.com', 'akdflakd') RETURNING student_id;
DELETE FROM student;


UPDATE student SET name = ?, email = ?, comments = ? WHERE student_id = ?
SELECT * From student
DELETE FROM student WHERE student_id = 4;


CREATE TABLE pair 
(student_name varchar(255) not null, 
second_student varchar(255) not null);

CREATE TABLE students_to_pair 
(student_name varchar(255) not null);


INSERT INTO students_to_pair (student_name) VALUES ('a');
INSERT INTO students_to_pair (student_name) VALUES ('b');
INSERT INTO students_to_pair (student_name) VALUES ('c');
INSERT INTO students_to_pair (student_name) VALUES ('d');
INSERT INTO students_to_pair (student_name) VALUES ('e');
INSERT INTO students_to_pair (student_name) VALUES ('f');
INSERT INTO students_to_pair (student_name) VALUES ('g');

INSERT INTO student (name, email, comments) VALUES (?, ?, ?)
INSERT INTO class_student(class_id, student_id) 
VALUES (?, ?)


SELECT student.student_id, student.name, email, comments FROM student 
JOIN class_student ON class_student.student_id = student.student_id 
JOIN class ON class.class_id = class_student.class_id 
WHERE class.class_id = 3

CREATE TABLE match_ex
(match_id int not null,
student_id_1 int not null,
student_id_2 int not null,
student_id_3 int,
size int not null,
week int not null,
count_of_matches int not null,

constraint fk_match_ex foreign key (student_id_1) references student(student_id),
foreign key (student_id_2) references student(student_id),
foreign key (student_id_3) references student(student_id));

SELECT * from match_ex

SELECT match_ex.match_id, student.name, student.name, student.name, size, week, count_of_matches FROM match_ex
JOIN student ON student.student_id = match_ex.student_id_1  
WHERE student_id = ?

SELECT * From student
INSERT INTO student_match_ex (match_id, student_id) VALUES (?, ?);

CREATE TABLE student_match_ex (match_id int not null, student_id int not null);
INSERT INTO match_ex (match_id, student_id_1, student_id_2, week, size, count_of_matches)
VALUES (3, ?, ?, ?, ?, ?) RETURNING match_id;

SELECT match_ex.match_id, 
        (SELECT name FROM student WHERE student_id = ?), 
        (SELECT name FROM student JOIN match_ex ON student.student_id = match_ex.student_id_2 WHERE student_id = ?), 
         size, 
         week, 
         count_of_matches 
FROM match_ex 
JOIN student ON student.student_id = match_ex.student_id_1 WHERE (student_id_1 = ?) OR student_id_2 = ?

SELECT s1.name, s2.name, m.week, m.count_of_matches
FROM match_ex m 
JOIN student s1 ON m.student_id_1 = s1.student_id
JOIN student s2 ON m.student_id_2 = s2.student_id
WHERE student_id_1 = 12 OR student_id_2 = 12
 
SELECT c.name, s1.name, s2.name, m.week, m.count_of_matches 
FROM match_ex m 
JOIN student s1 ON m.student_id_1 = s1.student_id
JOIN student s2 ON m.student_id_2 = s2.student_id
JOIN class_student cs ON cs.student_id = s1.student_id
JOIN class c ON c.class_id = cs.class_id
WHERE c.class_id = 3;

SELECT * FROM match_Ex
SELECT * FROM  class_student



--SELECT matches.match_id, student.name, student.name, student.name, size, week, count_of_matches FROM matches 
--JOIN student ON student.student_id = matches.student_id_1 
--JOIN class_student ON class_student.student_id = student.student_id 
--JOIN class ON class.class_id = class_student.class_id  
--JOIN app_user_class ON app_user_class.class_id = class.class_id 
--JOIN app_user ON app_user.id = app_user_class.id
--WHERE app_user.user_name = ? ORDER BY class.name DESC;

SELECT s1.name, s2.name, match_ex.week, c.name, match_ex.count_of_matches
FROM match_ex 
JOIN class_parameters param ON param.count_limit = match_ex.count_limit
JOIN class_parameters param2 ON param2.week = match_ex.week
JOIN class_parameters param3 ON param3.size = match_ex.

JOIN student s1 ON s1.student_id = match_ex.student_id_1
JOIN student s2 ON s2.student_id = match_ex.student_id_2
JOIN class_student cs ON s1.student_id = cs.student_id
JOIN class c ON c.class_id = cs.class_id 
JOIN app_user_class auc ON auc.class_id = c.class_id
JOIN app_user au ON au.id = auc.id
WHERE au.id = 1
ORDER BY c.name ASC

ALTER TABLE match_ex
  ADD count_limit int;
  
SELECT * FROM match_ex

INSERT INTO match_ex (match_id, count_limit, size, week) VALUES (?,?,?,?)

CREATE TABLE class_parameters
(param_id serial primary key,
count_limit int not null,
size int not null,
week int not null);

INSERT INTO class_parameters (count_limit, size, week) VALUES (?, ?, ?)
