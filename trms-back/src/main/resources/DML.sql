insert into user_role (role_id, role_name) 
	values (default, 'Employee'),
			(default, 'Direct Supervisor'),
	 		(default, 'Manager');
	 		
insert into status (status_id, status_name, approver)
	values (default, 'Pending Approval', 'Direct Supervisor'),
			(default, 'Approved', 'Manager'),
			(default, 'Denied', 'Employee'),
			(default, 'Requesting', 'Employee');
			
insert into grading_format (format_id, format_name, example)
	values (default,'Letter Grade','A/B/C'),
			(default,'Pass/Fail','Pass/Fail'),
			(default,'Percentage','85%'),
			(default,'Other',null);

insert into event_type (type_id, type_name, percent_coverage)
	values (default, 'Business Travel Expenses', 100.00),
			(default, 'Gas', 80.00),
			(default, 'Vacation', 35.50),
			(default, 'Home Office', 75.00),
			(default, 'Health and Fitness', 65.00),
			(default, 'Other', 30.00);
			
insert into department (dept_id, dept_name)
	values (default, 'Human Resources'),
			(default, 'Legal'),
			(default, 'Sales'),
			(default, 'Research and Development'),
			(default, 'Engineering'),
			(default, 'Business Development'),
			(default, 'IT');
		
insert into employee (emp_id, first_name, last_name, username, passwd, funds, role_id, dept_id)
	values (default, 'Ismael', 'Lee', 'smiley@email.com', 'smiley123', 12345.6, 3, 1),
			(default, 'Thesup', 'ervisor', 'thesupervisor@email.com', 'thesupman8', 3275, 2, 6);

insert into employee (emp_id, first_name, last_name, username, passwd, funds, supervisor_id, role_id, dept_id)
	values (default, 'Lelia', 'Biesterfeld', 'lbiesterfeld0@arstechnica.com', 'MOnWkB', 325, 2, 1, 7),
			(default, 'Heinrik', 'Jakoviljevic', 'hjakoviljevic1@newyorker.com', 'S29XjhSE', 3071, 2, 1, 2),
			(default, 'Heindrick', 'Kuna', 'hkuna2@linkedin.com', 'aLv461z1zC2', 1042, 2, 1, 1),
			(default, 'Derrik', 'Sewart', 'dsewart3@statcounter.com', 'Qm8Gxnko', 529, 2, 1, 4),
			(default, 'Terry', 'Cossentine', 'tcossentine4@alibaba.com', 'wFi3xZG2bC', 2689, 2, 1, 5),
			(default, 'Hetti', 'Mainstone', 'hmainstone5@cbsnews.com', 'zeBh2nObej', 4565, 2, 1, 6),
			(default, 'Bennie', 'Gannicleff', 'bgannicleff6@mapquest.com', 'WcEUqkPFQT', 1789, 2, 1, 3),
			(default, 'Tarra', 'Mullane', 'tmullane7@who.int', 'x9WHWW', 1980, 2, 1, 5),
			(default, 'Gordan', 'Hanley', 'ghanley8@foxnews.com', 'Wv9u5dv4BGDg', 2025, 2, 1, 7),
			(default, 'Myrlene', 'O''Brogan', 'mobrogan9@chronoengine.com', 'OlNY6KgWPP', 850, 2, 1, 4);

update employee 
	set supervisor_id = 1
	where emp_id = 2;


insert into reimbursement (req_id, emp_id, local_date, local_time, "location", description, "cost", grading_format_id, event_type_id, status_id, submitted_at)
	values (default, 5, '2022-01-02', '11:25:35', 'Home', 'Office Supplies', 1234.56, 3, 4, 4, current_timestamp);