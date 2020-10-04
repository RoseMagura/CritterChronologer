package com.udacity.critter.user.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
