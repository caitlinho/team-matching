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

create table matches
(match_id serial primary key,
size int not null,
week int not null,
count int not null);

create table student_matches
(student_id int not null,
match_id int not null,

constraint fk_student_macthes_match_id foreign key (match_id) references matches(match_id),
constraint fk_student_matches_student_id foreign key (student_id) references student(student_id));

create table app_user_class
(id int not null,
class_id int not null,

constraint fk_app_user_class_class_id foreign key (class_id) references class(class_id),
constraint fk_app_user_class_id foreign key (id) references app_user(id)
);
COMMIT;