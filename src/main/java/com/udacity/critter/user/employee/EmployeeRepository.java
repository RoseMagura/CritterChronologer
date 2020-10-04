package com.udacity.critter.user.employee;

import com.udacity.critter.user.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
