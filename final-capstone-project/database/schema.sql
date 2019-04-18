-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

create table class
(class_id serial primary key,
name varchar(100) not null);

create table student
(student_id serial primary key,
name varchar(255) not null,
email varchar(100) not null,
comments text);

CREATE TABLE matches
(match_id serial primary key,
student_id_1 int not null,
student_id_2 int not null,
student_id_3 int,
size int not null,
week int not null,
count_of_matches int not null,

constraint fk_matches foreign key (student_id_1) references student(student_id),
foreign key (student_id_2) references student(student_id),
foreign key (student_id_3) references student(student_id));

CREATE TABLE class_parameters
(param_id serial primary key,
count_limit int not null,
size int not null,
week int not null);

create table app_user_class
(id int not null,
class_id int not null,

constraint fk_app_user_class_class_id foreign key (class_id) references class(class_id),
constraint fk_app_user_class_id foreign key (id) references app_user(id)
);

create table class_student
(class_id int not null,
student_id int not null,

constraint fk_class_student_class_id foreign key (class_id) references class(class_id),
constraint fk_class_student_student_id foreign key (student_id) references student(student_id));
COMMIT;