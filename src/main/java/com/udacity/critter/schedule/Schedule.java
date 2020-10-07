package com.udacity.critter.schedule;


import com.udacity.critter.pet.Pet;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.employee.Employee;
import com.udacity.critter.user.employee.EmployeeSkill;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
//    @JoinColumn(name = "employee_id")
    private Set<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
//    @JoinColumn(name = "pet_id")
    private Set<Pet> pets;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
//    private Set<Customer> customers;

    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
