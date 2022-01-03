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