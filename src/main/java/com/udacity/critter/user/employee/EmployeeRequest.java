package com.udacity.critter.user.employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class EmployeeRequest {
    @Id
    @GeneratedValue
    Long id;

    @ElementCollection
    private Set<EmployeeSkill> skills;
    private LocalDate date;

    public EmployeeRequest(){}
    public EmployeeRequest(Set<EmployeeSkill> skills, LocalDate date) {
        this.skills = skills;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
