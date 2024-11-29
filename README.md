## Department CRUD

This project allows employees of the Human Resources (HR) department to manage university departments. Employees can perform CRUD operations (Create, Read, Update, Delete) for departments such as Administration, Accounts, etc. When creating a department, users must specify its capacity. Upon selecting a department, all employees and their details associated with that department will be displayed.

### Features
1. Perform CRUD operations on departments.
2. Display all employees within a selected department.
3. Manual input of employee details, including the HR department, is required for the initial setup.

---

### Backend (Using Spring Boot)
#### Controller Layer
- **DepartmentController**: Manages department-related requests such as creating, updating, deleting, and retrieving department details.
- **EmployeeController**: Handles employee-related requests, such as displaying employee data for a specific department.

#### Service Layer
- **DepartmentService**: Implements business logic related to department operations.
- **EmployeeService**: Implements business logic related to employee operations.

#### Repository Layer
- **DepartmentRepo**: Manages department data.
- **EmployeeRepo**: Manages employee data.

#### Model/Entity Layer
Entities representing database tables:
- **Department**: Represents university departments (`id`, `name`, `capacity`).
- **Employee**: Represents employees (`id`, `name`, `email`, `title`, `department_id`).

---

### Database Design
1. **Departments Table**
   - Fields: `id`, `capacity`, `name`

2. **Employees Table**
   - Fields: `id`, `name`, `email`, `title`, `department_id`

---

### Frontend (Optional, Using React)
If a frontend is implemented, it may include:
1. Pages for managing departments and viewing associated employees.
2. Employee CRUD operations (if extended).
3. Securely storing data with JWT (if authentication is added).

---

This README reflects the problem's requirements while leaving room for additional features you may want to implement. Let me know if you need further refinements!
