select * from user_role;

select * from department;

select * from employee;

select emp_id, first_name, last_name, username, passwd, employee.role_id, role_name, funds, supervisor_id, dept_id 
from employee join user_role on employee.role_id=user_role.role_id 
where username='smiley@email.com';


select * from grading_format;

select * from event_type;

select * from status;

select * from reimbursement;

select * from "comment";

select req_id, emp_id, event_date, event_time, location, 
		description, cost, grading_format_id, format_name, 
		example as format_example, event_type_id, type_name, percent_coverage, 
		r.status_id, status_name, approver, submitted_at  
from reimbursement r join grading_format gf 
on r.grading_format_id=gf.format_id 
join event_type et on r.event_type_id=et.type_id 
join status s 
on r.status_id=s.status_id 
where r.status_id=2;

