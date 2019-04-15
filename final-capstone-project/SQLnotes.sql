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
