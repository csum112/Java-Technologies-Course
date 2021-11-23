CREATE TABLE exams
(
    name     VARCHAR(50) PRIMARY KEY,
    start    TIME,
    duration INTEGER
);

CREATE TABLE students
(
    name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE STUDENTS_TO_EXAMS
(
    student_name VARCHAR(50),
    FOREIGN KEY (student_name) REFERENCES students (name),
    exam_name    VARCHAR(50),
    FOREIGN KEY (exam_name) REFERENCES exams (name),
    PRIMARY KEY(student_name, exam_name)
);
