DROP TABLE IF EXISTS school_db;

CREATE TABLE students (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(50) NOT NULL,
                               last_name VARCHAR(50) NOT NULL,
                               age INT
);