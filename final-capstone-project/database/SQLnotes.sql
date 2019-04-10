DROP TABLE IF EXISTS instructor_class;
DROP TABLE IF EXISTS student_matches;
DROP TABLE IF EXISTS instructor_student;
DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS instructor;



create table class
(
        class_id serial primary key,
        name varchar(100) not null
);
  
create table student
(
        student_id serial primary key, 
        name varchar(225) not null,
        email varchar(100) not null,
        comments text
);

create table matches
(
        match_id serial primary key, 
        size int not null, 
        week int not null,
        count int not null
);

create table instructor
(       
        instructor_id serial primary key,
        name varchar(225) not null, 
        password varchar(225) not null
);

create table instructor_class
(
        instructor_id int not null, 
        class_id int not null,
        
        constraint fk_instructor_class_instructor_id foreign key (instructor_id) references instructor(instructor_id), 
        constraint fk_instructor_class_class_id foreign key (class_id) references class(class_id)
);


create table student_matches
(
        student_id int not null, 
        match_id int not null, 
        
        constraint fk_student_matches_student_id foreign key (student_id) references student(student_id), 
        constraint fk_student_matches_match_id foreign key (match_id) references matches(match_id)
);

create table instructor_student
(
        instructor_id int not null,
        student_id int not null, 
        
        constraint fk_instructor_student_instructor_id foreign key (instructor_id) references instructor(instructor_id),
        constraint fk_instructor_student_student_id foreign key (student_id) references student(student_id)
);

alter table student add column active boolean

create table app_user_instructor
(
        instructor_id int not null, 
        id int not null, 
        
        constraint fk_app_user_instructor_instructor_id foreign key (instructor_id) references instructor(instructor_id), 
        constraint fk_app_user_instructor_id foreign key (id) references app_user(id)
);