package com.udacity.critter.user.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where :day member of e.daysAvailable")
    List<Employee> findByAvailability(DayOfWeek day);
}
