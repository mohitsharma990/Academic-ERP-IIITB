ALTER TABLE employees
ADD l_name VARCHAR(100),
ADD email VARCHAR(100) UNIQUE NOT NULL,
ADD title VARCHAR(100),
ADD photo_path VARCHAR(255);