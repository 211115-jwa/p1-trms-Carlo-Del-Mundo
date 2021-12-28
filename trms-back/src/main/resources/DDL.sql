create table department (
	deptId serial primary key,
	dept_name varchar(50),
	dept_head_Id integer 
		references department(deptId)
);

create table employee_role (
	role_id serial primary key,
	role_name varchar(50) not null
);

create table employee (
	emp_id serial primary key,
	firstName varchar(50) not null,
	lastName varchar(50),
	username varchar(50) unique,
	pwd varchar(50) not null,
	funds numeric,
	supervisor integer
		references employee(emp_id),
	employee_role integer not null
		references employee_role(role_id),
	department integer not null
		references department(deptId)
);

 create table employee_comments (
 	comment_id serial primary key,
 	comment_text varchar(500),
 	sent_at timestamp,
 	
 );
 
create table event_type (
	event_id serial primary key,
	event_name varchar(50) not null,
	percent_covered numeric
);

create table grading_format (
	format_id serial primary key,
	format_name varchar(50) not null,
	format_example varchar(500)
);

create table status (
	status_id serial primary key,
	status_name varchar(50),
	approver_role integer
		references employee_role(role_id)
);

create table reimbursement (
	reimbursement_id serial primary key,
	employee integer
		references employee(emp_id),
	local_date date,
	local_time time,
	site varchar(100),
	description varchar(500),
	cost_amount numeric,
	grading_format integer
		references grading_format(format_id),
	event_type integer
		references event_type(event_id),
	status integer
		references status(status_id),
	local_date_time timestamptz
);

create table emp_comments (
	comment_id serial primary key,
	reimbursement integer
		references reimbursement(reimbursement_id),
	employee integer
		references employee(emp_id),
	comment_text varchar(500),
	local_date_time timestamptz
);