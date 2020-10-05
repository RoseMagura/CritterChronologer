package com.udacity.critter.user.employee;

import com.udacity.critter.user.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where :day member of e.daysAvailable")
    List<Employee> findByAvailability(DayOfWeek day);
}