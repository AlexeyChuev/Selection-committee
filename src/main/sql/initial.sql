connect 'jdbc:derby://localhost:1527/myDB;create=true;user=admin;password=admin';

DROP TABLE Submission_Subject;
DROP TABLE Faculty_Subject;
DROP TABLE Grade_Type;
DROP TABLE Subject;
DROP TABLE Submission;
DROP TABLE Faculty;
DROP TABLE Enrollee;
DROP TABLE Administrator;
DROP TABLE Users;
DROP TABLE Role;

CREATE TABLE Role(
  id integer NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  role_type varchar(10) NOT NULL UNIQUE
);

CREATE TABLE Users(
  id integer NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  role INT REFERENCES Role (id),
  email varchar(60) NOT NULL UNIQUE,
  password VARCHAR (50) NOT NULL
);

CREATE TABLE Administrator(
  id integer NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  user_id INTEGER REFERENCES Users (id)
);

CREATE TABLE Enrollee(
  id integer NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  full_name varchar(70) NOT NULL,
  city varchar(30) NOT NULL,
  region varchar(30) NOT NULL,
  school_name varchar(50) NOT NULL,
  certificate BLOB,
  isBlocked BOOLEAN DEFAULT FALSE,
  user_id INTEGER REFERENCES Users (id)
);

CREATE TABLE Faculty(
  id INT NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  name varchar(50) NOT NULL UNIQUE,
  budgetVolume integer NOT NULL,
  totalVolume integer NOT NULL
);

CREATE TABLE Subject(
  id INT NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  name varchar(60) NOT NULL UNIQUE
);

CREATE TABLE Faculty_Subject(
  id INT NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  faculty_id integer REFERENCES Faculty (id),
  subject_id integer REFERENCES Subject (id),
  UNIQUE (faculty_id, subject_id)
);

CREATE TABLE Submission(
  id INT NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  faculty_id integer REFERENCES Faculty (id),
  enrollee_id integer REFERENCES Enrollee (id),
  UNIQUE (faculty_id, enrollee_id)
);

CREATE TABLE Grade_Type(
  id integer NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  grade_type VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE Submission_Subject(
  id INT NOT NULL PRIMARY KEY generated always AS identity (START WITH 1, INCREMENT BY 1),
  submission_id integer REFERENCES Submission (id),
  subject_id integer REFERENCES Subject (id),
  grade INT NOT NULL,
  grade_type VARCHAR(20) REFERENCES Grade_Type(id),
  UNIQUE (subject_id, submission_id)
);

disconnect;
exit;


