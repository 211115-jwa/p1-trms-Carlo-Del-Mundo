create table department (
	dept_id serial primary key,
	dept_name varchar(50),
	dept_head_id integer 
		references department(dept_id)
);

create table user_role (
	role_id serial primary key,
	role_name varchar(50) not null
);

create table employee (
	emp_id serial primary key,
	first_name varchar(50) not null,
	last_name varchar(50),
	username varchar(50) unique,
	passwd varchar(50) not null,
	funds numeric,
	supervisor_id integer
		references employee(emp_id),
	role_id integer
		references user_role(role_id),
	dept_id integer
		references department(dept_id)
);

alter table department
	drop constraint department_dept_head_id_fkey,
	add foreign key (dept_head_id) references employee(emp_id);

create table event_type (
	type_id serial primary key,
	type_name varchar(50) not null,
	percent_coverage numeric
);

create table grading_format (
	format_id serial primary key,
	format_name varchar(50) not null,
	example varchar(50)
);

create table status (
	status_id serial primary key,
	status_name varchar(50),
	approver varchar(50)
);

create table reimbursement (
	req_id serial primary key,
	emp_id integer
		references employee(emp_id),
	local_date date,
	local_time time,
	location varchar(300),
	description varchar(500),
	cost numeric,
	grading_format_id integer
		references grading_format(format_id),
	event_type_id integer
		references event_type(type_id),
	status_id integer
		references status(status_id),
	submitted_at timestamptz
);

create table comment (
	comment_id serial primary key,
	req_id integer
		references reimbursement(req_id),
	approver_id integer
		references employee(emp_id),
	comment_text varchar(500),
	sent_at timestamptz
);
