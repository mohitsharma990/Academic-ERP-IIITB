# Department CRUD

This project allows employees of the Human Resources (HR) department to log in and manage various departments at a university. Employees can perform CRUD operations (Create, Read, Update, Delete) for departments such as Admin, Accounts, etc. When creating a department, users must specify its capacity. Upon selecting a department, all employees and their details associated with that department will be displayed.

Manually input employee details, including the HR department, for the first entry.


Backend (Using Spring Boot)

Controller Layer

	•	DepartmentController: Manages department-related requests such as creating, updating, deleting, and retrieving department details.
	•	EmployeeController: Handles employee-related requests such as displaying employee data for a specific department.

Service Layer

	•	DepartmentService: Implements business logic related to department operations.
	•	EmployeeService: Implements business logic related to employee operations.

Repository Layer

	•	DepartmentRepo: Manages department data.
	•	EmployeeRepo: Manages employee data.


Model/Entity Layer

Entities that represent the database tables:
	•	Department: Represents university departments (id, name, capacity).
	•	Employee: Represents employees (id, first name, last name, email, department).

Database Design

	1.	Departments
	•	Fields: department_id, capacity, name, department
	2.	Employees
	•	Fields: first_name, last_name, email, title, salary, photograph_path, password, department_id


Frontend (Using React)

	1.	Employees log in by entering credentials (e.g., email and password), which sends a POST request to the backend.
	2.	Upon successful authentication, the backend returns a JWT token.
	3.	The frontend stores the JWT token securely (e.g., in local storage or cookies) and includes it in subsequent requests.
	4.	Once logged in, the employee can see a list of employees, where those in a department such as “Faculty” have a checkbox next to them.
	5.	Selecting a faculty employee enables a “Disburse” button to allow salary disbursement.
	6.	Pages for employee registration and employee modifications are also implemented.

