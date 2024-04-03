/*
 Each time this file is executed, it will reset the database to the original state defined below.
 You can import this directly in your database by (a) manually entering the first three lines of
 commands form this file, (b) removing the first three lines of commands from this file, and
 (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).

 During grading, TAs will assume that these two tables exist, but will enter different values.
 Thus you cannot assume that any of the values provided here exist, but you can assume the tables
 exist.

 You may optionally create additional tables in the ensf380project database with demonstration 
 data, provided that you provide the information in a valid SQL file which TAs can import and
 clearly include this information in your instructions.
 */


DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project


CREATE TABLE INQUIRER (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20) NOT NULL
);
INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES
(1, 'Dominik', 'Pflug', '123-456-9831'),
(2, 'Yaa', 'Odei', '123-456-8913'),
(3, 'Cecilia', 'Cobos', '123-456-7891'),
(4, 'Hongjoo', 'Park', '123-456-8912');
INSERT INTO INQUIRER (id, firstName, phoneNumber) VALUES
(5, 'Saartje', '123-456-7234'),
(6, 'Urjoshi', '456-123-4281');

CREATE TABLE INQUIRY_LOG (
    id SERIAL PRIMARY KEY,
    inquirer int NOT NULL,
    callDate DATE NOT NULL,
    details VARCHAR(500) NOT NULL,
    foreign key (inquirer) references INQUIRER(id) ON UPDATE CASCADE
);

INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES
(1, 1, '2024-02-28', 'Theresa Pflug'),
(2, 2, '2024-02-28', 'Offer to assist as volunteer'),
(3, 3, '2024-03-01', 'Valesk Souza'),
(4, 1, '2024-03-01', 'Theresa Pflug'),
(5, 1, '2024-03-02', 'Theresa Pflug'),
(6, 4, '2024-03-02', 'Yoyo Jefferson and Roisin Fitzgerald'),
(7, 5, '2024-03-02', 'Henk Wouters'),
(8, 3, '2024-03-03', 'Melinda'),
(9, 6, '2024-03-04', 'Julius');

CREATE TABLE disastervictim (
    first_name VARCHAR(150),
    last_name VARCHAR(150),
    date Date
);

INSERT INTO disastervictim (first_name, last_name, date) VALUES
('John', "Smith", 2024-04-01),
("Kayla", "Smith", 2013-09-18),
("Kamand", "Ghorbanzadeh", 2024-01-12),
("Sepand", "Ghorbanzadeh", 2001-01-23);

CREATE TABLE familyrelation (
    first_name1 VARCHAR(150),
    last_name1 VARCHAR(150),
    first_name2 VARCHAR(150),
    last_name2 VARCHAR(150),
    relationship_type VARCHAR(150)
);

INSERT INTO familyrelation (first_name1, last_name1, first_name2, last_name2, relationship_type) VALUES
('John', "Smith", "Kayla", "Smith", "sibling"),
("Kamand", "Ghorbanzadeh", "Sepand", "Ghorbanzadeh", "parent");

CREATE TABLE medicalrecord (
    location_name VARCHAR(150),
    treatment_detail VARCHAR(150),
    date_of_treatment Date
);


INSERT INTO medicalrecord (location_name, treatment_detail, date_of_treatment) VALUES
("Shelter", "headache", 2001-09-12),
("Shelter", "headache", 2003-01-12),
("Central", "stomachache", 2001-12-02);