--CONNECT 'jdbc:derby://localhost:1527/myDB;create=true;user=admin;password=admin';

DROP TABLE Enrollee;
DROP TABLE Faculty;
DROP TABLE Subject;
DROP TABLE Faculty_Subject;
DROP TABLE Submission;
DROP TABLE Submission_Subject;

CREATE TABLE Enrollee(
  id integer NOT NULL generated always AS identity PRIMARY KEY,
  first_name varchar(20) NOT NULL,
  last_name varchar(20) NOT NULL,
  surname varchar(20) NOT NULL,
  city varchar(30) NOT NULL,
  region varchar(30) NOT NULL,
  school_name varchar(50) NOT NULL,
  email varchar(60) NOT NULL UNIQUE ,
  certificate BLOB,
  isBlocked BOOLEAN DEFAULT FALSE
);

CREATE TABLE Faculty(
  id INT NOT NULL generated always AS identity PRIMARY KEY,
  name varchar(50) NOT NULL UNIQUE,
  budget_volume integer NOT NULL,
  total_volume integer NOT NULL
);

CREATE TABLE Subject(
  id INT NOT NULL generated always AS identity PRIMARY KEY,
  name varchar(60) NOT NULL UNIQUE
);

CREATE TABLE Faculty_Subject(
  id INT NOT NULL generated always AS identity PRIMARY KEY,
  faculty_id integer REFERENCES Faculty (id),
  subject_id integer REFERENCES Subject (id)
);

CREATE TABLE Submission(
  id INT NOT NULL generated always AS identity PRIMARY KEY,
  faculty_id integer REFERENCES Faculty (id),
  enrollee_id integer REFERENCES Enrollee (id)
);

CREATE TABLE Submission_Subject(
  id INT NOT NULL generated always AS identity PRIMARY KEY,
  submission_id integer REFERENCES Submission (id),
  subject_id integer REFERENCES Subject (id),
  grade INT NOT NULL
)

--DISCONNECT;

