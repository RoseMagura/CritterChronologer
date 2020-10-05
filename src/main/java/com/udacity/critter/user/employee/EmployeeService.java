package com.udacity.critter.user.employee;

import com.udacity.critter.schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityManager entityManager;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public List<Employee> getAvailableEmployees(DayOfWeek day) {
        return employeeRepository.findByAvailability(day);
    }

    public void updateSchedule(Set<DayOfWeek> daysAvailable, long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        System.out.println("from service: " + employee.getDaysAvailable());
        employeeRepository.save(employee);
    }
//    private static final String FIND_EMPLOYEE_BY_SKILLS_AND_AVAILABILITY =
//            "select e from Employee e where e.skills = :skills ";
//    public List<Employee> findBySkillsAndAvailability(Set<EmployeeSkill> skills, LocalDate day) {
//        System.out.println("Service: " + skills.toString());
//        TypedQuery<Employee> query = entityManager.createQuery(FIND_EMPLOYEE_BY_SKILLS_AND_AVAILABILITY, Employee.class);
//        query.setParameter("skills", skills);
//        return query.getResultList();
//    }

}
