-- departements definition

-- Drop table

-- DROP TABLE departements;

CREATE TABLE departements (
	dept_no bpchar(4) NOT NULL,
	dept_name varchar(40) NULL,
	CONSTRAINT departements_pkey PRIMARY KEY (dept_no)
);

INSERT INTO departements
(dept_no, dept_name)
VALUES('IT01', 'Information Technology');
INSERT INTO departements
(dept_no, dept_name)
VALUES('FN01', 'Finance');
INSERT INTO departements
(dept_no, dept_name)
VALUES('AC01', 'Accounting');
INSERT INTO departements
(dept_no, dept_name)
VALUES('MK01', 'Marketing & Strategic');
INSERT INTO pdepartements
(dept_no, dept_name)
VALUES('BD01', 'Bussiness Development');


-- dept_emp definition

-- Drop table

-- DROP TABLE dept_emp;

CREATE TABLE dept_emp (
	dept_no bpchar(4) NOT NULL,
	emp_no numeric(11) NOT NULL,
	from_date date NULL,
	to_date date NULL,
	CONSTRAINT dept_emp_pkey PRIMARY KEY (dept_no, emp_no)
);

INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 8, '2022-12-03', '2023-12-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('MK01', 9, '2022-12-01', '2023-12-01');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('MK01', 12, '2021-01-31', '2023-01-31');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('BD01', 14, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('BD01', 15, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 18, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 19, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 20, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 21, '2022-10-03', '2022-10-03');
INSERT INTO dept_emp
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 22, '2022-10-03', '2022-10-03');



-- dept_manager definition

-- Drop table

-- DROP TABLE dept_manager;

CREATE TABLE dept_manager (
	dept_no bpchar(4) NOT NULL,
	emp_no numeric(11) NOT NULL,
	from_date date NULL,
	to_date date NULL,
	CONSTRAINT dept_manager_pkey PRIMARY KEY (dept_no, emp_no)
);

INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('MK01', 10, '2021-11-01', '2023-11-01');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('MK01', 11, '2021-12-03', '2024-12-03');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('BD01', 16, '2022-10-03', '2023-10-03');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 17, '2022-03-21', '2025-03-21');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 18, '2019-12-03', '2024-12-03');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('IT01', 19, '2020-12-03', '2025-12-03');
INSERT INTO dept_manager
(dept_no, emp_no, from_date, to_date)
VALUES('FN01', 20, '2021-08-03', '2023-08-03');


-- employees definition

-- Drop table

-- DROP TABLE employees;

CREATE TABLE employees (
	emp_no numeric(11) NOT NULL,
	birth_date date NULL,
	first_name varchar(14) NULL,
	gender bpchar(1) NULL,
	hire_date date NULL,
	last_name varchar(16) NULL,
	CONSTRAINT employees_pkey PRIMARY KEY (emp_no)
);


INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(8, '1989-10-31', 'Yohansen', 'M', '2022-12-03', 'Octavianto');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(9, '1990-11-30', 'Ismail', 'M', '2022-12-01', 'Marjuki');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(10, '1992-02-25', 'Tina', 'F', '2022-12-01', '');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(11, '1982-02-25', 'Dina', 'F', '2022-12-01', 'Suryadina');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(12, '1982-02-25', 'Indari', 'F', '2021-01-07', 'Atmajaya');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(14, '1980-12-03', 'Andri', 'M', '2022-09-03', 'Hendrawan');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(15, '1980-12-03', 'Andika', 'M', '2022-09-03', 'Primawan');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(16, '1980-12-03', 'Petra', 'M', '2022-09-03', 'Antera');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(17, '1980-12-03', 'Dhika', 'M', '2022-09-03', 'Akbar');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(18, '1980-12-03', 'Julius', 'M', '2022-09-03', 'Sitanggang');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(19, '1980-12-03', 'Ferdy', 'M', '2022-09-03', 'Sambo');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(20, '1980-12-03', 'Lehman', 'M', '2022-09-03', '');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(21, '1980-12-03', 'Ronaldo', 'M', '2022-09-03', '');
INSERT INTO employees
(emp_no, birth_date, first_name, gender, hire_date, last_name)
VALUES(22, '1980-12-03', 'Triana', 'F', '2022-09-03', 'Andriani');

-- salaries definition

-- Drop table

-- DROP TABLE salaries;

CREATE TABLE salaries (
	emp_no numeric(11) NOT NULL,
	from_date date NOT NULL,
	salary numeric(11) NULL,
	to_date date NULL,
	CONSTRAINT salaries_pkey PRIMARY KEY (emp_no, from_date)
);

INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(8, '2022-12-03', 7000000, '2023-12-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(9, '2022-12-01', 5000000, '2023-12-01');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(10, '2022-11-01', 6500000, '2023-11-01');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(11, '2022-10-01', 11000000, '2023-10-01');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(12, '2021-01-31', 8000000, '2023-01-31');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(16, '2022-10-03', 6850000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(19, '2022-10-03', 6850000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(20, '2022-10-03', 6850000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(14, '2022-10-03', 4500000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(15, '2022-10-03', 12000000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(17, '2022-10-03', 5200000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(18, '2022-10-03', 4500000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(21, '2022-10-03', 7250000, '2022-10-03');
INSERT INTO salaries
(emp_no, from_date, salary, to_date)
VALUES(22, '2022-10-03', 20000000, '2022-10-03');

-- titles definition

-- Drop table

-- DROP TABLE titles;

CREATE TABLE titles (
	emp_no numeric(11) NOT NULL,
	from_date date NOT NULL,
	title varchar(50) NOT NULL,
	to_date date NULL,
	CONSTRAINT titles_pkey PRIMARY KEY (emp_no, from_date, title)
);

INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(8, '2022-12-03', 'S.E', '2023-12-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(9, '2022-12-01', 'S.E', '2023-12-01');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(10, '2022-11-01', 'S.E', '2023-11-01');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(11, '2022-10-01', 'S.E', '2023-10-01');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(12, '2021-01-31', 'Sarjana Pertanian', '2023-01-31');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(14, '2022-10-03', 'Spd', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(15, '2022-10-03', 'Skom', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(16, '2022-10-03', 'Skom', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(17, '2022-10-03', 'Skom', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(18, '2022-10-03', 'Skom', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(19, '2022-10-03', 'SE', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(20, '2022-10-03', 'SMA', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(21, '2022-10-03', 'Mkom', '2022-10-03');
INSERT INTO titles
(emp_no, from_date, title, to_date)
VALUES(22, '2022-10-03', 'MM', '2022-10-03');
